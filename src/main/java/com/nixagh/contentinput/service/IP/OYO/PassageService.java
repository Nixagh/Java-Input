package com.nixagh.contentinput.service.IP.OYO;

import com.nixagh.contentinput.modal.PassageTab;
import com.nixagh.contentinput.modal.excel.GT.WordTieSheet;
import com.nixagh.contentinput.modal.excel.OYO.PassageSheet;
import com.nixagh.contentinput.modal.excel.WordListSheet;
import com.nixagh.contentinput.repository.PassageRepository;
import com.nixagh.contentinput.repository.QuestionRepository;
import com.nixagh.contentinput.service.VWABaseService;
import com.nixagh.contentinput.util.ExcelReader;
import javax.persistence.EntityManager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author nghia.nguyen-dinh
 * @since 11/7/2023 at 1:50 PM
 */
@Getter
@Setter
public class PassageService extends VWABaseService {
    private PassageSheet passageSheet;
    private final String passageSheetName = "OnLevelPsg";
    private Long[] currentPassageIds = new Long[1];

    private String questionContentVMpath = "src/main/java/com/nixagh/contentinput/libs/Vm/passage/QuestionContent.vm";
    private String passageContentVMpath = "src/main/java/com/nixagh/contentinput/libs/Vm/passage/PassageContent.vm";
    private String passageSummaryVMpath = "src/main/java/com/nixagh/contentinput/libs/Vm/passage/PassageSummary.vm";

    public PassageService(ExcelReader excelReader, QuestionRepository questionRepository, PassageRepository passageRepository, EntityManager entityManager) {
        super(excelReader, questionRepository, passageRepository, entityManager);
    }

    public void createQuestion() {

        var content = passageSheet.getPassageBody();
        var summary = passageSheet.getChoicePageSummaryText();
        var directionLine = passageSheet.getDirectionLine();
        var image = passageSheet.getChoicePagePhoto();
        var wordListSheet = new WordListSheet();
        wordListSheet.setWordID("");
        wordListSheet.setP1Set("A");
        wordListSheet.setP2Set("B");

        this.createPartA(content, summary, directionLine, image, wordListSheet);
        this.createPartB(content, summary, directionLine, image, wordListSheet);

        IntStream.range(2, 11).forEach((index) -> this.createQuestion(index, wordListSheet, content, summary, directionLine, image));
    }

    private void createQuestion(int index, WordListSheet wordListSheet, String content, String summary, String directionLine, String image) {
        var item = passageSheet.getValue("Item " + index);
        var itemChoices = passageSheet.getValue("Item " + index + " Choices");
        var itemCorrectAnswer = passageSheet.getValue("Item " + index + " Correct answer");
        var itemStandards = passageSheet.getValue("Item " + index + " Standards");
        var itemPoints = passageSheet.getValue("Item " + index + " Points");

        var questionNumber = index + 1;

        var questionTab = this.buildQuestionTab(wordListSheet, itemStandards, questionNumber);
        var passageTab = this.buildPassageTab(content, summary, directionLine, image, questionNumber);

        var questionContent = this.buildQuestionContent(item, itemChoices, questionNumber);
        var correctAnswer = this.buildCorrectAnswer(itemChoices, itemCorrectAnswer, questionNumber);
        var questionContentTab = this.buildQuestionContentTab(questionContent, correctAnswer, questionNumber);

        var feedbackTab = this.buildFeedbackTab(item);

        this.createQuestion(questionTab, passageTab, questionContentTab, feedbackTab);
    }

    private void createPartA(String content, String summary, String directionLine, String image,
                             WordListSheet wordListSheet) {
        this.createPart(content, summary, directionLine, image, wordListSheet, "A", 1);
    }

    private void createPartB(String content, String summary, String directionLine, String image,
                             WordListSheet wordListSheet) {
        this.createPart(content, summary, directionLine, image, wordListSheet, "B", 2);
    }

    private void createPart(String content, String summary, String directionLine, String image,
        WordListSheet wordListSheet, String set, int questionNumber) {
        var item = passageSheet.getValue("Item 1 Part " + set);
        var itemChoices = passageSheet.getValue("Item 1 Part B Answer Choices");
        if (itemChoices == null) itemChoices = passageSheet.getValue("Item 1 Part " + set + " Choices");
        var itemCorrectAnswer = passageSheet.getValue("Item 1 Part " + set + " Correct Answer");
        var itemStandards = passageSheet.getValue("Item 1 Part " + set + " Standard");

        var questionTab = this.buildQuestionTab(wordListSheet, itemStandards, questionNumber);
        var passageTab = this.buildPassageTab(content, summary, directionLine, image, questionNumber);

        var questionContent = this.buildQuestionContent(item, itemChoices, questionNumber);
        var correctAnswer = this.buildCorrectAnswer(itemChoices, itemCorrectAnswer, questionNumber);
        var questionContentTab = this.buildQuestionContentTab(questionContent, correctAnswer, questionNumber);

        var feedbackTab = this.buildFeedbackTab(item);

        this.createQuestion(questionTab, passageTab, questionContentTab, feedbackTab);
    }

    private WordListSheet getWordListByItem(String item) {
        return this.wordListSheets.stream()
            .filter(x -> item.contains(x.getWord()))
            .findFirst()
            .orElse(null);
    }

    private PassageTab buildPassageTab(String content, String summary, String directionLine, String image, int questionNumber) {
        var passageTab = new PassageTab();

        var passageContent = this.buildPassageContent(content);
        var passageSummary = this.buildPassageSummary(summary, image);

        passageTab.setChoicePassage(true);
        passageTab.setContent(passageContent);
        passageTab.setSummary(passageSummary);
        passageTab.setDirectionLine(directionLine);

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

    private String buildPassageContent(String content) {
        Map<String, Object> map = Map.of("title", this.getPassageTitle(content), "body", this.passageBodyConvert(content), "productCode", this.getProductCode());

        return this.convertFromVMFile(this.passageContentVMpath, map);
    }

    private String getPassageTitle(String content) {
        var regex = "<title>(?<title>.+?)</title>";
        var matcher = this.getMatcher(regex, content);

        if (matcher.find()) {
            return matcher.group("title");
        }
        return "";
    }

    private String buildPassageSummary(String summary, String image) {
        var regex = "<title>(?<title>.+?)</title>";
        var matcher = this.getMatcher(regex, summary);

        var title = "";
        if (matcher.find()) {
            title = matcher.group("title");
        }

        var description = summary.replace(regex, "").trim();

        Map<String, Object> map = Map.of("title", title, "description", description, "image", image);

        return this.convertFromVMFile(this.passageSummaryVMpath, map);
    }

    private String buildQuestionContent(String item, String itemChoices, int questionNumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("questionNumber", questionNumber);
        map.put("item", item);
        map.put("cid", this.getCID(questionNumber));
        map.put("options", this.getOptions(itemChoices, questionNumber));

        return this.convertFromVMFile(this.questionContentVMpath, map);
    }

    private String getOptions(String itemChoices, int questionNumber) {
        var choices = this.getChoices(itemChoices, questionNumber);
        return choices.stream()
            .map(this::toHTML)
            .collect(Collectors.joining());
    }

    private String toHTML(Option option) {
        return String.format("<div itemid=\"%s\" itemlabel=\"\">%s</div>", option.id, option.content);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class Option {
        String id;
        String content;
    }

    private List<Option> getChoices(String itemChoices, int questionNumber) {
        var options = itemChoices.split("(; |;)[abcd]\\.( |)");
        if (options.length != 4) {
            this.addError("answerChoices", "answerChoices must have 4 options at question number: " + questionNumber);
        }
        return IntStream.range(0, options.length)
            .mapToObj(i -> new Option((char) ('a' + i) + "", options[i].trim().replaceAll("[abcd]\\. ", "").trim()))
            .collect(Collectors.toList());
    }

    private String buildCorrectAnswer(String itemChoices, String correctAnswers, int questionNumber) {
        var choices = this.getChoices(itemChoices, questionNumber);
        var _correctAnswers = correctAnswers.replaceAll("[abcd]\\. ", "");
        return choices.stream()
            .filter(choice -> choice.content.equals(_correctAnswers))
            .map(Option::getId)
            .collect(Collectors.joining());
    }

    private String buildFeedbackTab(String item) {
        var regex = "paragraph (?<paragraph>\\d+)";
        var matcher = this.getMatcher(regex, item);

        var paragraph = "";
        if (matcher.find()) {
            var temp = matcher.group("paragraph");
            paragraph = String.format("{\"paragraphId\": \"%s\"}",temp);
        }
        return paragraph;
    }
}
