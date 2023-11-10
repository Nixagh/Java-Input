package com.nixagh.contentinput.service.IP.OYO;

import com.nixagh.contentinput.common.enums.VWAEnums;
import com.nixagh.contentinput.domain.model.excel.OYO.DifferentiatedPassageSheet;
import com.nixagh.contentinput.domain.repository.PassageRepository;
import com.nixagh.contentinput.domain.repository.QuestionRepository;
import com.nixagh.contentinput.service.VWABaseService;
import com.nixagh.contentinput.util.ExcelReader;
import javax.persistence.EntityManager;

/**
 * @author nghia.nguyen-dinh
 * @since 11/7/2023 at 1:32 PM
 */
public class DifferentiatedPassage2 extends PassageService {
    public DifferentiatedPassage2(ExcelReader excelReader, QuestionRepository questionRepository, PassageRepository passageRepository, EntityManager entityManager) {
        super(excelReader, questionRepository, passageRepository, entityManager);
    }

    public String getType() {
        return VWAEnums.DifferentiatedPassage2.getType();
    }

    public Integer getAdaptiveAnswerCount() {
        return VWAEnums.DifferentiatedPassage2.getAdaptiveAnswerCount();
    }

    public boolean getSetPassage() {
        return VWAEnums.DifferentiatedPassage2.isPassage();
    }

    public void init() {
        super.init();
        var temp = this.excelReader.getExcelFile(this.path, DifferentiatedPassageSheet.class);
        this.setPassageSheet(temp.get(1));
    }
}
