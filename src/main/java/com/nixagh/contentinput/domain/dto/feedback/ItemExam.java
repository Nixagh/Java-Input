package com.nixagh.contentinput.domain.dto.feedback;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ItemExam implements Serializable {
    private String item;
    private String correctAnswerFeedback;
    private String incorrectFeedback1;
    private String incorrectFeedback2;
}
