package com.nixagh.contentinput.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nghia.nguyen-dinh
 * @since 11/6/2023 at 12:57 PM
 */
@Getter
@Setter
public class QuestionTab {
    private Integer questionNumber;
    private Integer questionTypeId;
    private String standard;
    private boolean autoScore;
    private Integer linkToQuestion;
    private String wordId;
    private String pathwaySet1;
    private String pathwaySet2;
    private Integer adaptiveAnswerCount;
}
