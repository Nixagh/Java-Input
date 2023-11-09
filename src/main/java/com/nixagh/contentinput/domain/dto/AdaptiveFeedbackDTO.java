package com.nixagh.contentinput.domain.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AdaptiveFeedbackDTO implements Serializable {

    private List<String> correctFeedback;
    private List<String> incorrectFeedback1;
    private List<String> incorrectFeedback2;
    private List<String> incorrectFeedback3;
    private List<String> incorrectFeedback4;
    private List<String> correctAnswerEmoji;
    private List<String> incorrectAnswerEmoji;
    private List<String> correctEmoji;
    private List<String> incorrectEmojiFB1;
    private List<String> incorrectEmojiFB2;
    private List<String> correctAnswerFeedback;
    private List<String> incorrectEmoji1;
    private List<String> incorrectEmoji2;
    private List<String> incorrectEmoji3;
    private List<String> incorrectEmoji4;
    private List<ItemExam> items;
    private String visualClue;
    private String paragraphId;

    //On your own
    private List<String> incorrectFeedback;
    private List<String> incorrectEmoji;
}
