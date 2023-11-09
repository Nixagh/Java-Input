package com.nixagh.contentinput.domain.dto.feedback;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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

    // to json
    public String convert2Json() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            // but if field is null, it will not be included in json string
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error when convert to json");
            return "";
        }
    }
}
