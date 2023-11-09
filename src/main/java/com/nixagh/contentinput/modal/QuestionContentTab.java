package com.nixagh.contentinput.modal;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nghia.nguyen-dinh
 * @since 11/6/2023 at 1:02 PM
 */

@Getter
@Setter
public class QuestionContentTab {
    private String content;
    private String correctAnswer;
    private String correctAnswerTextHtml;

    public void setCorrectAnswer(String cid, String value, String type, String subtype) {
        this.correctAnswer = """
                {"comps":[{"id":"%s","value":"%s","type":"%s"%s}]}
                """.formatted(cid, value, type, subtype != null ? ",\"subtype\":\"" + subtype + "\"" : "");
    }
}
