package com.nixagh.contentinput.service.IP.SP;

import com.nixagh.contentinput.common.enums.VWAEnums;
import com.nixagh.contentinput.domain.model.excel.SP.DefinitionSheet;
import com.nixagh.contentinput.domain.repository.PassageRepository;
import com.nixagh.contentinput.domain.repository.QuestionRepository;
import com.nixagh.contentinput.util.ExcelReader;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author nghia.nguyen-dinh
 * @since 11/6/2023 at 2:00 PM
 */
@Getter
@Setter
public class DefinitionService extends SPService {
    private String questionContentVMPath = "src/main/java/com/nixagh/contentinput/libs/Vm/definition/QuestionContent.vm";

    public Integer getAdaptiveAnswerCount() {
        return VWAEnums.Definitions.getAdaptiveAnswerCount();
    }

    public String getType() {
        return VWAEnums.Definitions.getType();
    }

    public boolean getSetPassage() {
        return VWAEnums.Definitions.isPassage();
    }

    public DefinitionService(ExcelReader excelReader, QuestionRepository questionRepository, PassageRepository passageRepository, EntityManager entityManager) {
        super(excelReader, questionRepository, passageRepository, entityManager);
    }

    public void createQuestion() {
        IntStream.range(0, this.definitionSheets.size())
            .forEach(this::createQuestion);
    }

    private void createQuestion(int index) {
        var definition = this.definitionSheets.get(index);

        var wordID = definition.getWordID();
        var standard = definition.getStandard();
        var correctAnswers = definition.getCorrectAnswers();
        var content = this.buildContent(definition, index + 1);

        this.createQuestion(wordID, standard, content, correctAnswers, definition, index + 1);
    }

    // ================ //
    public String buildContent(DefinitionSheet definitionSheet, int questionNumber) {
        var inflectedFormsHTML = this.getInflectedFormsHTML(definitionSheet.getInflectedForms());
        var synAntBody = this.getSynAntBody(definitionSheet.getSynonyms(), definitionSheet.getAntonyms());
        var questionHTMl = this.getQuestionHTML(definitionSheet.getExampleSentence(), questionNumber);

        Map<String, Object> map = Map.of(
            "inflectedFormsHTML", inflectedFormsHTML,
            "synAntBody", synAntBody,
            "questionHTMl", questionHTMl
        );

        return this.convertFromVMFile(this.questionContentVMPath, map);
    }

    public String getInflectedFormsHTML(String inflectedForms) {
        if (inflectedForms == null || inflectedForms.isEmpty()) {
            return "";
        }

        var regex = "<i>|<(/|)i>";
        inflectedForms = inflectedForms.replaceAll(regex, "");

        return String.format("<div class=\"inflected-forms\"><i>%s</i></div>",inflectedForms);
    }

    public String getSynAntBody(String synonyms, String antonyms) {
        if (synonyms == null || synonyms.isEmpty() || synonyms.equals("N/A")) {
            synonyms = "";
        }
        if (antonyms == null || antonyms.isEmpty() || antonyms.equals("N/A")) {
            antonyms = "";
        }

        var _synonyms = !synonyms.isEmpty() ? "<b>SYNONYMS </b> " + synonyms : "";
        var _antonyms = !antonyms.isEmpty() ? !synonyms.isEmpty() ? "<br/> <b>ANTONYMS </b> " + antonyms : "<b>ANTONYMS </b> " + antonyms : "";

        return String.format("<div class=\"syn-ant-body\">%s %s</div>", _synonyms, _antonyms);
    }

    public String getQuestionHTML(String item, Integer questionNumber) {
        var cid = this.getCID(questionNumber);

        var input = String.format("<input autocapitalize=\"off\" autocomplete=\"off\" autocorrect=\"off\" cid=\"%s\" ctype=\"Fill_in_Blank\" qname=\"a%d\" spellcheck=\"false\" subtype=\"word\" type=\"text\" />",cid, questionNumber);

        var regex = "\\[(.+?)]";
        var question = item.replaceAll(regex, input);

        return String.format("<div class=\"question\">%s</div>", question);
    }
}
