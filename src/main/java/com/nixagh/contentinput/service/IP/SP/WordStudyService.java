package com.nixagh.contentinput.service.IP.SP;

import com.nixagh.contentinput.common.enums.VWAEnums;
import com.nixagh.contentinput.modal.excel.SP.WordStudySheet;
import com.nixagh.contentinput.repository.PassageRepository;
import com.nixagh.contentinput.repository.QuestionRepository;
import com.nixagh.contentinput.util.ExcelReader;
import javax.persistence.EntityManager;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author nghia.nguyen-dinh
 * @since 11/6/2023 at 2:07 PM
 */
@Getter
@Setter
public class WordStudyService extends SPService {
    private String questionContentVMPath = "src/main/java/com/nixagh/contentinput/libs/Vm/wordStudy/QuestionContent.vm"

    public Integer getAdaptiveAnswerCount() {
        return VWAEnums.WordStudy.getAdaptiveAnswerCount();
    }

    public String getType() {
        return VWAEnums.WordStudy.getType();
    }

    public boolean getSetPassage() {
        return VWAEnums.WordStudy.isPassage();
    }

    public WordStudyService(ExcelReader excelReader, QuestionRepository questionRepository, PassageRepository passageRepository, EntityManager entityManager) {
        super(excelReader, questionRepository, passageRepository, entityManager);
    }


    public void createQuestion() {
        IntStream.range(0, this.wordStudySheets.size()).forEach(this::createQuestion);
    }

    private void createQuestion(int index) {
        var wordStudy = this.wordStudySheets.get(index);
        this.createQuestion(wordStudy.getWordID(), wordStudy.getStandard(), this.buildContent(wordStudy, index + 1), "", wordStudy, index + 1);
    }

    public String buildContent(WordStudySheet wordStudySheet, int questionNumber) {
        var inflectedFormsHTML = this.getInflectedFormsHTML(wordStudySheet.getInflectedForm());
        var characteristicsImageHTML = this.getCharacteristicsImageHTML(wordStudySheet.getCharacteristicImage());

        var isMultipleMeaning = characteristicsImageHTML.contains("Multiple-meaning");

        var prefixHTML = this.getPrefixHTML(wordStudySheet.getPrefix());
        var rootOrBaseHTMl = this.getRootORBaseHTMl(wordStudySheet.getRootOrBase());
        var suffixHTML = this.getSuffixHTML(wordStudySheet.getSuffix());
        var introductionHTML = this.getIntroductionHTML(wordStudySheet.getIntroduction());
        var firstSampleHTML = this.getFirstSampleHTML(wordStudySheet.getSampleSentence1());
        var firstExplainHTML = this.getFirstExplainHTML(wordStudySheet.getSampleSentence1Explanation());
        var firstAltText = isMultipleMeaning ? "<div class=\"first-alttext\"></div>" : "";
        var secondSampleHTML = this.getSecondSampleHTML(wordStudySheet.getSampleSentence2());
        var secondExplainHTML = this.getSecondExplainHTML(wordStudySheet.getSampleSentence2Explanation());
        var secondAltText = isMultipleMeaning ? "<div class=\"second-alttext\"></div>" : "";
        var wordJounalPromtHTML = this.getWordJounalPromtHTML(wordStudySheet.getWordJournalPrompt());
        var leftImageHTML = isMultipleMeaning ? this.getLeftImageHTML(wordStudySheet.getImageDetails()) : "";
        var rightImageHTML = isMultipleMeaning ? this.getRightImageHTML(wordStudySheet.getImageDetails()) : "";

        Map<String, Object> map = new HashMap<>();
        map.put("inflectedFormsHTML", inflectedFormsHTML);
        map.put("characteristicsImageHTML", characteristicsImageHTML);
        map.put("prefixHTML", prefixHTML);
        map.put("rootOrBaseHTMl", rootOrBaseHTMl);
        map.put("suffixHTML", suffixHTML);
        map.put("introductionHTML", introductionHTML);
        map.put("firstSampleHTML", firstSampleHTML);
        map.put("firstExplainHTML", firstExplainHTML);
        map.put("firstAltText", firstAltText);
        map.put("secondSampleHTML", secondSampleHTML);
        map.put("secondExplainHTML", secondExplainHTML);
        map.put("secondAltText", secondAltText);
        map.put("wordJounalPromtHTML", wordJounalPromtHTML);
        map.put("leftImageHTML", leftImageHTML);
        map.put("rightImageHTML", rightImageHTML);
        map.put("questionNumber", questionNumber);
        map.put("cid", this.getCID(questionNumber));
        
        return this.convertFromVMFile(this.questionContentVMPath, map);
    }

    private String getInflectedFormsHTML(String inflectedForm) {
        if (inflectedForm == null || inflectedForm.isEmpty()) return "";
        return String.format("<div class=\"inflected-forms\"><i>%s</i></div>", inflectedForm.replaceAll("<i>|<(/|)i>", ""));
    }

    private String getCharacteristicsImageHTML(String characteristicsImage) {
        if (characteristicsImage == null || characteristicsImage.isEmpty()) return "";

        if (characteristicsImage.contains("Multiple Meaning"))
            characteristicsImage = characteristicsImage.replace("Multiple Meaning", "Multiple-meaning");

        return String.format("<div class=\"characteristics-image\">%s</div>", characteristicsImage);
    }

    private String getPrefixHTML(String prefix) {
        if (prefix == null || prefix.isEmpty()) return "";

        var box = "<div class=\"prefix\">";

        return this.convert(prefix, box);
    }

    private String getRootORBaseHTMl(String rootOrBase) {
        if (rootOrBase == null || rootOrBase.isEmpty()) return "";

        var box = "<div class=\"root-or-base\">";

        return this.convert(rootOrBase, box);
    }

    private String getSuffixHTML(String suffix) {
        if (suffix == null || suffix.isEmpty()) return "";

        var box = "<div class=\"suffix\">";

        return this.convert(suffix, box);
    }

    private String convert(String content, String box) {
        var result = content.split("\n");
        // even line add <br></br>

        IntStream.range(0, result.length).forEach(i -> {
            if (i % 2 == 0) {
                result[i] = "<b>" + result[i] + "</b><br/>";
            }
        });

        return Arrays.stream(String.join("<br/>", result)
                .split("<br/><b>"))
            .map(x -> box + x + "</div>")
            .reduce("", String::concat);
    }

    private String getIntroductionHTML(String intro) {
        if (intro == null || intro.isEmpty()) return "";
        return String.format("<div class=\"introduction\">%s</div>", intro);
    }

    private String getFirstSampleHTML(String sample) {
        if (sample == null || sample.isEmpty()) return "";
        return String.format("<div class=\"first-sample\">%s</div>", sample);
    }

    private String getFirstExplainHTML(String explain) {
        if (explain == null || explain.isEmpty()) return "";
        return String.format("<div class=\"first-explain\">%s</div>", explain);
    }

    private String getSecondSampleHTML(String sample) {
        if (sample == null || sample.isEmpty()) return "";
        return String.format("<div class=\"second-sample\">%s</div>", sample);
    }

    private String getSecondExplainHTML(String explain) {
        if (explain == null || explain.isEmpty()) return "";
        return String.format("<div class=\"second-explain\">%s</div>", explain);
    }

    private String getWordJounalPromtHTML(String wordJounalPromt) {
        return "Now write your own sentence using the vocabulary word.";
    }

    private String getLeftImageHTML(String imageDetails) {
        return this.getImageHTML(imageDetails, 1);
    }

    private String getRightImageHTML(String imageDetails) {
        return this.getImageHTML(imageDetails, 2);
    }

    private String getImageHTML(String imageDetails, int index) {
        if (imageDetails == null || imageDetails.isEmpty()) return "";

        // template: "<image>802910_U2_4035_PH_WS_succeed2</image>";
        var image = imageDetails.replaceAll("<image>|</image>", "");
        // template: "802910_U2_4035_PH_WS_succeed2";
        // remove last number
        image = image.substring(0, image.length() - 1) + index;

        return String.format("<img src=\"/cms/repository/cms/images2020/%s.jpg\" style=\"width: 334px; height: 234px;\" />", image);
    }
}
