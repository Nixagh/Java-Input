package com.nixagh.contentinput.service.IP.OYO;

import com.nixagh.contentinput.common.enums.VWAEnums;
import com.nixagh.contentinput.domain.model.excel.OYO.DifferentiatedPassageSheet;
import com.nixagh.contentinput.domain.model.excel.OYO.OnLevelPassageSheet;
import com.nixagh.contentinput.domain.repository.PassageRepository;
import com.nixagh.contentinput.domain.repository.QuestionRepository;
import com.nixagh.contentinput.service.VWABaseService;
import com.nixagh.contentinput.util.ExcelReader;
import javax.persistence.EntityManager;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nghia.nguyen-dinh
 * @since 11/7/2023 at 1:32 PM
 */
@Getter
@Setter
public class OnLVPassage1 extends PassageService {
    private final String passageSheetName = "OnLevelPsg";

    public OnLVPassage1(ExcelReader excelReader, QuestionRepository questionRepository, PassageRepository passageRepository, EntityManager entityManager) {
        super(excelReader, questionRepository, passageRepository, entityManager);
    }

    public String getType() {
        return VWAEnums.OnLevelPassage1.getType();
    }

    public Integer getAdaptiveAnswerCount() {
        return VWAEnums.OnLevelPassage1.getAdaptiveAnswerCount();
    }

    public boolean getSetPassage() {
        return VWAEnums.OnLevelPassage1.isPassage();
    }

    public void init() {
        super.init();
        var temp = this.excelReader.getExcelFile(this.path, this.passageSheetName, OnLevelPassageSheet.class);
        this.setPassageSheet(temp.get(0));
    }
}
