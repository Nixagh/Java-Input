package com.nixagh.contentinput.service.SP;

import com.nixagh.contentinput.common.enums.VWAEnums;
import com.nixagh.contentinput.modal.excel.SP.DefinitionSheet;
import com.nixagh.contentinput.repository.PassageRepository;
import com.nixagh.contentinput.repository.QuestionRepository;
import com.nixagh.contentinput.util.ExcelReader;
import javax.persistence.EntityManager;

import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author nghia.nguyen-dinh
 * @since 11/7/2023 at 8:29 AM
 */
public class VisualService extends SPService {
    public VisualService(ExcelReader excelReader, QuestionRepository questionRepository, PassageRepository passageRepository, EntityManager entityManager) {
        super(excelReader, questionRepository, passageRepository, entityManager);
    }

    public Integer getAdaptiveAnswerCount() {
        return VWAEnums.Visuals.getAdaptiveAnswerCount();
    }

    public String getType() {
        return VWAEnums.Visuals.getType();
    }

    public boolean getSetPassage() {
        return VWAEnums.Visuals.isPassage();
    }

    public void createQuestion() {
        IntStream.range(0, this.definitionSheets.size())
            .forEach(this::createQuestion);
    }

    public void createQuestion(int index) {
        var definition = this.definitionSheets.get(index);
        this.createQuestion(definition.getWordID(), definition.getStandard(), this.buildContent(definition, index + 1), "", definition, index + 1);
    }

    private String buildContent(DefinitionSheet definitionSheet, int questionNumber) {
        var dataSources = this.getDataSources(definitionSheet.getInstructionalVideoPickupCode(), definitionSheet.getWord());

        Map<String, Object> map = Map.of(
            "questionNumber", questionNumber,
            "dataSources", dataSources,
            "cid", this.getCID(questionNumber)
        );

        return this.addByVM("src/main/java/com/nixagh/contentinput/libs/Vm/visual/QuestionContent.vm", map);
    }

    private String getDataSources(String instructionalVideoPickupCode, String word) {
        if ("new".equalsIgnoreCase(instructionalVideoPickupCode)) {
            return "/content/802906/007744939/VW_unavailablevideo.mp4";
        }

        return String.format("/content/802906/%s/%s.mp4", this.getResourceCode(), word);
    }
}
