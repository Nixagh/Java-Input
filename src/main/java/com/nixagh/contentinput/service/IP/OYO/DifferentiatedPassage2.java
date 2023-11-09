package com.nixagh.contentinput.service.IP.OYO;

import com.nixagh.contentinput.domain.repository.PassageRepository;
import com.nixagh.contentinput.domain.repository.QuestionRepository;
import com.nixagh.contentinput.service.VWABaseService;
import com.nixagh.contentinput.util.ExcelReader;
import javax.persistence.EntityManager;

/**
 * @author nghia.nguyen-dinh
 * @since 11/7/2023 at 1:32 PM
 */
public class DifferentiatedPassage2 extends VWABaseService {
    public DifferentiatedPassage2(ExcelReader excelReader, QuestionRepository questionRepository, PassageRepository passageRepository, EntityManager entityManager) {
        super(excelReader, questionRepository, passageRepository, entityManager);
    }
}
