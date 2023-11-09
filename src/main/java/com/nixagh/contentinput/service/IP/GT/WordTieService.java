package com.nixagh.contentinput.service.IP.GT;

import com.nixagh.contentinput.common.enums.VWAEnums;
import com.nixagh.contentinput.domain.dto.feedback.AdaptiveFeedbackDTO;
import com.nixagh.contentinput.modal.PassageTab;
import com.nixagh.contentinput.modal.excel.GT.WordTieSheet;
import com.nixagh.contentinput.repository.PassageRepository;
import com.nixagh.contentinput.repository.QuestionRepository;
import com.nixagh.contentinput.service.VWABaseService;
import com.nixagh.contentinput.util.ExcelReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author nghia.nguyen-dinh
 * @since 11/7/2023 at 1:31 PM
 */
public class WordTieService extends VWABaseService {
    private List<WordTieSheet> wordTieSheets;
    private String wordTieSheetName = "WordTies";
    private Long[] currentPassageIds = new Long[1];

    private String QuestionContentVMpath = "src/main/java/com/nixagh/contentinput/libs/Vm/wordTies/WordTieQuestionContent.vm";

    public WordTieService(ExcelReader excelReader, QuestionRepository questionRepository, PassageRepository passageRepository, EntityManager entityManager) {
        super(excelReader, questionRepository, passageRepository, entityManager);
    }

    public String getType() {
        return VWAEnums.WordTies.getType();
    }

    public Integer getAdaptiveAnswerCount() {
        return VWAEnums.WordTies.getAdaptiveAnswerCount();
    }

    public boolean getSetPassage() {
        return VWAEnums.WordTies.isPassage();
    }

    public void init() {
        super.init();
        this.wordTieSheets = excelReader.getExcelFile(this.path, this.wordTieSheetName, WordTieSheet.class);
    }

    public void createQuestion() {
        IntStream.range(0, this.wordTieSheets.size()).forEach(this::createQuestion);
    }

    public void createQuestion(int index) {
        var wordTie = this.wordTieSheets.get(index);
        var wordId = wordTie.getWordID();
        var standard = wordTie.getStandard();
        index = index + 1;

        try {
            var wordListSheet = this.getWordList(wordId);

            if (wordListSheet == null) {
                System.out.println("word id: " + wordId + " not found in word list" + " at item: " + index);
                return;
            }

            var content = this.buildContent(wordTie, index);
            var correctAnswer = this.getCorrectAnswer(wordTie.getCorrectAnswers(), wordTie.getAnswerChoices(), index);

            var questionTab = this.buildQuestionTab(wordListSheet, standard, index);

            var passageTab = this.buildPassageTab(wordTie, index);

            var questionContentTab = this.buildQuestionContentTab(content, correctAnswer, index);

            var feedbackTab = this.buildFeedbackTab(wordTie);

            this.createQuestion(questionTab, passageTab, questionContentTab, feedbackTab);
        } catch (Exception e) {
            System.out.println(this.getError("wrong at item: " + index + " of definition"));
            e.printStackTrace();
        }
    }

    private String buildContent(WordTieSheet wordTieSheet, int questionNumber) {
        var item = wordTieSheet.getItem();
        var answerChoices = wordTieSheet.getAnswerChoices();

        Map<String, Object> map = Map.of(
            "item", item,
            "cid", this.getCID(questionNumber),
            "questionNumber", questionNumber,
            "options", this.getOptions(answerChoices, questionNumber)
        );

        return this.convertFromVMFile(QuestionContentVMpath, map);
    }

    private String getOptions(String answerChoices, int questionNumber) {
        return this.getChoices(answerChoices, questionNumber)
            .stream()
            .map(this::toHtml)
            .collect(Collectors.joining());
    }

    private String toHtml(Option option) {
        return String.format("<div itemid=\"%s\" itemlabel=\"\">%s</div>", option.id, option.content);
    }

    private String getCorrectAnswer(String correctAnswers, String answerChoices, int questionNumber) {
        var options = this.getChoices(answerChoices, questionNumber);
        var _correctAnswers = correctAnswers.split("; |;");

        return options.stream().filter(option -> Arrays.asList(_correctAnswers).contains(option.content))
            .map(option -> option.id)
            .collect(Collectors.joining(","));
    }
    @Getter
    @Setter
    @AllArgsConstructor
    private static class Option {
        String id;
        String content;
    }
    private List<Option> getChoices(String answerChoices, int questionNumber) {
        var options = answerChoices.split("; |;");
        if (options.length != 4) {
            this.addError("answerChoices", "answerChoices must have 4 options at question number: " + questionNumber);
        }
        return IntStream.range(0, options.length)
            .mapToObj(i -> new Option((char) ('a' + i) + "", options[i].trim()))
            .collect(Collectors.toList());
    }

    private PassageTab buildPassageTab(WordTieSheet wordTieSheet, int questionNumber) {
        var passageTab = new PassageTab();
        passageTab.setDirectionLine(wordTieSheet.getDirectionLine());
        if (questionNumber != 1) {
            // get passage from previous question
            if (currentPassageIds[0] == null) {
                currentPassageIds[0] = this.getPassages(1).get(0).getPassageId();
            }
            passageTab.setPassageId(currentPassageIds[0]);
            return passageTab;
        }

        return passageTab;
    }

    private String buildFeedbackTab(WordTieSheet wordTieSheet) {
        var correctFeedback = List.of("");
        var incorrectFeedback1 = this.convertAtFeedBack(wordTieSheet.getIncorrectFeedback1());
        var incorrectFeedback2 = this.convertAtFeedBack(wordTieSheet.getIncorrectFeedback2());
        var incorrectFeedback3 = this.convertAtFeedBack(wordTieSheet.getIncorrectFeedback3());
        var incorrectFeedback4 = this.convertAtFeedBack(wordTieSheet.getIncorrectFeedback4());

        var correctEmoji = wordTieSheet.getCorrectEmoji();
        var incorrectEmoji1 = wordTieSheet.getIncorrectEmoji1();
        var incorrectEmoji2 = wordTieSheet.getIncorrectEmoji2();
        var incorrectEmoji3 = wordTieSheet.getIncorrectEmoji3();
        var incorrectEmoji4 = wordTieSheet.getIncorrectEmoji4();

        return AdaptiveFeedbackDTO.builder()
            .correctFeedback(correctFeedback)
            .incorrectFeedback1(List.of(incorrectFeedback1))
            .incorrectFeedback2(List.of(incorrectFeedback2))
            .incorrectFeedback3(List.of(incorrectFeedback3))
            .incorrectFeedback4(List.of(incorrectFeedback4))
            .correctEmoji(correctEmoji)
            .incorrectEmoji1(incorrectEmoji1)
            .incorrectEmoji2(incorrectEmoji2)
            .incorrectEmoji3(incorrectEmoji3)
            .incorrectEmoji4(incorrectEmoji4)
            .build()
            .convert2Json();
    }
}
