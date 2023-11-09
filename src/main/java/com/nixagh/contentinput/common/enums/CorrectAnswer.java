package com.nixagh.contentinput.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nghia.nguyen-dinh
 * @since 11/7/2023 at 11:13 AM
 */
public class CorrectAnswer {
    public static Map<String, Comp> compMap= new HashMap<>();

    static {
        compMap.put("GT_CTRW", new Comp("Drop_Down", null));
        compMap.put("GT_EN", new Comp("Drag_n_Drop_Adaptive", null));
        compMap.put("GT_VIC_D", new Comp("MultipleChoice", null));
        compMap.put("GT_VinC_O", new Comp("MultipleChoice", null));
        compMap.put("GT_WT", new Comp("MultipleChoice", null));
        compMap.put("OYO_AP", new Comp("MultipleChoice", null));
        compMap.put("OYO_CTRW", new Comp("Drop_Down", null));
        compMap.put("OYO_CTS", new Comp("Drop_Down", null));
        compMap.put("OYO_DP1", new Comp("MultipleChoice", null));
        compMap.put("OYO_DP2", new Comp("MultipleChoice", null));
        compMap.put("OYO_OLP1", new Comp("MultipleChoice", null));
        compMap.put("OYO_OLP2", new Comp("MultipleChoice", null));
        compMap.put("definitions", new Comp("Fill_in_Blank", "word"));
        compMap.put("InstructionVideo", new Comp("Video", null));
        compMap.put("wordstudy", new Comp("View", null));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Comp {
        private String type;
        private String subtype;
    }
}
