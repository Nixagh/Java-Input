package com.nixagh.contentinput.service.GT;

import com.nixagh.contentinput.common.enums.VWAEnums;
import com.nixagh.contentinput.entities.Passage;
import com.nixagh.contentinput.modal.PassageTab;
import com.nixagh.contentinput.modal.excel.GT.WordTieSheet;
import com.nixagh.contentinput.modal.excel.SP.DefinitionSheet;
import com.nixagh.contentinput.repository.PassageRepository;
import com.nixagh.contentinput.repository.QuestionRepository;
import com.nixagh.contentinput.service.VWABaseService;
import com.nixagh.contentinput.util.ExcelReader;
import jakarta.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;
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
        return """
            <div class="question-questionStem question-questionStem-1-column">
                <div class="question-stem-content">
                   <div class="question">%s<br />
                       <br />
                       <div cid="%s" ctype="MultipleChoice" layout="Vertical" qname="a%d" subtype="MSC">
                           %s
                       </div>
                   </div>
                </div>
            </div>""".formatted(item, this.getCID(questionNumber), questionNumber, this.getOptions(answerChoices, questionNumber));
    }

    private String getOptions(String answerChoices, int questionNumber) {
        return this.getChoices(answerChoices, questionNumber)
            .stream()
            .map(this::toHtml)
            .collect(Collectors.joining());
    }

    private String toHtml(Option option) {
        return """
            <div itemid="%s" itemlabel="">%s</div>
            """.formatted(option.id, option.content);
    }

    private String getCorrectAnswer(String correctAnswers, String answerChoices, int questionNumber) {
        var options = this.getChoices(answerChoices, questionNumber);
        var _correctAnswers = correctAnswers.split("; |;");

        return options.stream().filter(option -> Arrays.asList(_correctAnswers).contains(option.content))
            .map(option -> option.id)
            .collect(Collectors.joining(","));
    }
    private record Option(String id, String content) {}
    private List<Option> getChoices(String answerChoices, int questionNumber) {
        var options = answerChoices.split("; |;");
        if (options.length != 4) {
            this.addError("answerChoices", "answerChoices must have 4 options at question number: " + questionNumber);
        }
        return IntStream.range(0, options.length)
            .mapToObj(i -> new Option((char) ('a' + i) + "", options[i].trim()))
            .toList();
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
        var correctFeedback = this.toListOfString("");
        var incorrectFeedback1 = this.toListOfString(this.convertAtFeedBack(wordTieSheet.getIncorrectFeedback1()));
        var incorrectFeedback2 = this.toListOfString(this.convertAtFeedBack(wordTieSheet.getIncorrectFeedback2()));
        var incorrectFeedback3 = this.toListOfString(this.convertAtFeedBack(wordTieSheet.getIncorrectFeedback3()));
        var incorrectFeedback4 = this.toListOfString(this.convertAtFeedBack(wordTieSheet.getIncorrectFeedback4()));

        var correctEmoji = wordTieSheet.getCorrectEmoji();
        var incorrectEmoji1 = wordTieSheet.getIncorrectEmoji1();
        var incorrectEmoji2 = wordTieSheet.getIncorrectEmoji2();
        var incorrectEmoji3 = wordTieSheet.getIncorrectEmoji3();
        var incorrectEmoji4 = wordTieSheet.getIncorrectEmoji4();

        return """
            {"correctFeedback": %s, "incorrectFeedback1": %s, "incorrectFeedback2": %s, "incorrectFeedback3": %s, "incorrectFeedback4": %s, "correctEmoji": %s, "incorrectEmoji1": %s, "incorrectEmoji2": %s, "incorrectEmoji3": %s, "incorrectEmoji4": %s}
            """.formatted(correctFeedback, incorrectFeedback1, incorrectFeedback2, incorrectFeedback3, incorrectFeedback4, correctEmoji, incorrectEmoji1, incorrectEmoji2, incorrectEmoji3, incorrectEmoji4);
    }

    private String toListOfString(String str) {
        return """
            ["%s"]""".formatted(str);
    }
}
