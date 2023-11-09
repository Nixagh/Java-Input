package com.nixagh.contentinput.service;

import com.nixagh.contentinput.domain.repository.PassageRepository;
import com.nixagh.contentinput.domain.repository.QuestionRepository;
import com.nixagh.contentinput.service.IP.SP.SPService;
import com.nixagh.contentinput.util.ExcelReader;
import javax.persistence.EntityManager;

public class WordListService extends SPService {

    public WordListService(ExcelReader excelReader,
        QuestionRepository questionRepository,
        PassageRepository passageRepository,
        EntityManager entityManager) {
        super(excelReader, questionRepository, passageRepository, entityManager);
    }

}
