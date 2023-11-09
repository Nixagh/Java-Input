package com.nixagh.contentinput.service.SP;

import com.nixagh.contentinput.common.enums.VWAEnums;
import com.nixagh.contentinput.modal.excel.SP.DefinitionSheet;
import com.nixagh.contentinput.repository.PassageRepository;
import com.nixagh.contentinput.repository.QuestionRepository;
import com.nixagh.contentinput.util.ExcelReader;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import java.util.stream.IntStream;

/**
 * @author nghia.nguyen-dinh
 * @since 11/6/2023 at 2:00 PM
 */
@Getter
@Setter
public class DefinitionService extends SPService {
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

        return """
            <div class="question-questionStem question-questionStem-1-column">
                <div class="question-stem-content">
                    %s
                    %s
                    %s
                </div>
            </div>""".formatted(inflectedFormsHTML, synAntBody, questionHTMl).trim();
    }

    public String getInflectedFormsHTML(String inflectedForms) {
        if (inflectedForms == null || inflectedForms.isEmpty()) {
            return "";
        }

        var regex = "<i>|<(/|)i>";
        inflectedForms = inflectedForms.replaceAll(regex, "");

        return """
            <div class="inflected-forms"><i>%s</i></div>""".formatted(inflectedForms);
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

        return """
            <div class="syn-ant-body">%s %s</div>"""
            .formatted(_synonyms, _antonyms);
    }

    public String getQuestionHTML(String item, Integer questionNumber) {
        var cid = this.getCID(questionNumber);

        var input = """
            <input autocapitalize="off" autocomplete="off" autocorrect="off" cid="%s" ctype="Fill_in_Blank" qname="a%d" spellcheck="false" subtype="word" type="text" />"""
            .formatted(cid, questionNumber);

        var regex = "\\[(.+?)]";
        var question = item.replaceAll(regex, input);

        return """
            <div class="question">%s</div>""".formatted(question);
    }
}
