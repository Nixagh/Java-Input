package com.nixagh.contentinput;

import com.nixagh.contentinput.domain.dto.feedback.AdaptiveFeedbackDTO;
import com.nixagh.contentinput.domain.model.excel.SP.DefinitionSheet;
import com.nixagh.contentinput.util.ExcelReader;
import com.nixagh.contentinput.util.FileUtil;
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

    @Test
    void TestReadSheetNameContain() {
        var sheetName = "Def";
        var path = "D:\\Work\\.Data\\G10\\802910_IP_lvE_u1-8\\802910_IP_lvE_u1-8\\assets\\resources\\VWSA25_InsAndPrac_g10E\\B1_U1-8_SP\\VWA_LevE_U7_SP.xlsx";
        var excelReader = new ExcelReader(new FileUtil());
        var sheetNameFound = excelReader.getExcelFile(path, sheetName, DefinitionSheet.class);
        System.out.println(sheetNameFound);
    }
}
