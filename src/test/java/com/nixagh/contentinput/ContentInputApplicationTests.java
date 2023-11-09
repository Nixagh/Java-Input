package com.nixagh.contentinput;

import com.nixagh.contentinput.domain.dto.feedback.AdaptiveFeedbackDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContentInputApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        var feedback = new AdaptiveFeedbackDTO();
        feedback.setParagraphId("123");
        feedback.setVisualClue("456");

        System.out.println(feedback.convert2Json());
    }
}
