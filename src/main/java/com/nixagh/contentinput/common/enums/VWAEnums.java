package com.nixagh.contentinput.common.enums;

import lombok.Getter;

/**
 * @author nghia.nguyen-dinh
 * @since 11/6/2023 at 2:08 PM
 */
@Getter
public enum VWAEnums {
    ChoosingTheRightWordGT(2, null, "GT_CTRW", false),
    ExampleNonExample(2, null, "GT_EN", false),
    VocabularyInContextDifferentiated(2, null, "GT_VIC_D", false),
    VocabularyInContextOnLevel(2, null, "GT_VinC_O", false),
    WordTies(2, null, "GT_WT", true),
    AdaptivePractice(1, null, "OYO_AP", false),
    ChoosingTheRightWordOYO(null, 1, "OYO_CTRW", false),
    CompletingTheSentence(null, 1, "OYO_CTS", false),
    DifferentiatedPassage1(null, 1, "OYO_DP1", true),
    DifferentiatedPassage2(null, 1, "OYO_DP2", true),
    OnLevelPassage1(null, 1, "OYO_OLP1", true),
    OnLevelPassage2(null, 1, "OYO_OLP2", true),
    Definitions(2, null, "definitions", false),
    Visuals(null, null, "InstructionVideo", false),
    WordStudy(null, null, "wordstudy", false);
    final Integer adaptiveAnswerCount;
    final Integer retryCount;
    final String type;
    final boolean isPassage;
    VWAEnums(Integer adaptiveAnswerCount, Integer retryCount, String type, boolean isPassage) {
        this.adaptiveAnswerCount = adaptiveAnswerCount;
        this.retryCount = retryCount;
        this.type = type;
        this.isPassage = isPassage;
    }
}
