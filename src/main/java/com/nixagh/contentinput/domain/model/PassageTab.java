package com.nixagh.contentinput.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nghia.nguyen-dinh
 * @since 11/6/2023 at 1:00 PM
 */
@Getter
@Setter
public class PassageTab {
    private Long passageId;
    private boolean scramble;
    private boolean choicePassage;
    private String content;
    private String summary;
    private String directionLine;
}
