package com.nixagh.contentinput.modal.excel.OYO;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelUnknownCells;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author nghia.nguyen-dinh
 * @since 11/8/2023 at 1:14 PM
 */
@Getter
@Setter
public class PassageSheet {
    //Step	Choice Passage	Pathway	Choice page summary text	Choice Page Photo 	Direction Line 	On-Level Passage Body	Lexile	Item Type 	Item 1 Part A	Item 1 Part A Choices 	Item 1 Part A Correct Answer 	Item 1 Part A Standards	Item 1 Part A Points	Item 1 Part B	Item 1 Part B Answer Choices 	Item 1 Part B Correct Answer	Item 1 Part B Standards	Item 1 Part B Points	Item 2	Item 2  Choices	Item 2 Correct answer 	Item 2 Standards	Item 2 Points 	Item 3 	Item 3 Choices 	Item 3 Correct answer 	Item 3 Standards	Item 3 Points 	Item 4 	Item 4 Choices 	Item 4 Correct answer 	Item 4 Standards	Item 4 Points 	Item 5	Item 5 Choices 	Item 5 Correct answer 	Item 5 Standards	Item 5 Points 	Item 6	Item 6 Choices 	Item 6 correct answer	Item 6 Standards	Item 6 Points	Item 7	Item 7 Choices 	Item 7 correct answer 	Item 7 Standards	Item 7 Points	Item 8 	Item 8 Choices 	Item 8 Correct Answer 	Item 8 Standards	Item 8 Points	Item 9	Item 9 Choices 	Item 9 Correct Answer 	Item 9 Standards	Item 9 Points	Item 10	Item 10 Choices 	Item 10 Correct Answer 	Standard 	Item 10 Points
    @ExcelCellName("Step")
    private String step;
    @ExcelCellName("Choice Passage")
    private String choicePassage;
    @ExcelCellName("Pathway")
    private String pathway;
    @ExcelCellName("Choice page summary text")
    private String choicePageSummaryText;
    @ExcelCellName("Choice Page Photo")
    private String choicePagePhoto;
    private String directionLine = "<i>Read the following passage, taking note of the boldface words and their contexts. Use the tools provided to annotate the text as you read. Then answer the questions.</i>";
    @ExcelCellName("Lexile")
    private String lexile;
    @ExcelCellName("Item Type")
    private String itemType;

//    @ExcelCellName("Item 1 Part A")
//    private String item1PartA;
//    @ExcelCellName("Item 1 Part A Choices")
//    private String item1PartAChoices;
//    @ExcelCellName("Item 1 Part A Correct Answer")
//    private String item1PartACorrectAnswer;
//    @ExcelCellName("Item 1 Part A Standards")
//    private String item1PartAStandards;
//    @ExcelCellName("Item 1 Part A Points")
//    private String item1PartAPoints;
//
//    @ExcelCellName("Item 1 Part B")
//    private String item1PartB;
//    @ExcelCellName("Item 1 Part B Answer Choices")
//    private String item1PartBAnswerChoices;
//    @ExcelCellName("Item 1 Part B Correct Answer")
//    private String item1PartBCorrectAnswer;
//    @ExcelCellName("Item 1 Part B Standards")
//    private String item1PartBStandards;
//    @ExcelCellName("Item 1 Part B Points")
//    private String item1PartBPoints;
//
//    @ExcelCellName("Item 2")
//    private String item2;
//    @ExcelCellName("Item 2  Choices")
//    private String item2Choices;
//    @ExcelCellName("Item 2 Correct answer")
//    private String item2CorrectAnswer;
//    @ExcelCellName("Item 2 Standards")
//    private String item2Standards;
//    @ExcelCellName("Item 2 Points")
//    private String item2Points;
//
//    @ExcelCellName("Item 3")
//    private String item3;
//    @ExcelCellName("Item 3 Choices")
//    private String item3Choices;
//    @ExcelCellName("Item 3 Correct answer")
//    private String item3CorrectAnswer;
//    @ExcelCellName("Item 3 Standards")
//    private String item3Standards;
//    @ExcelCellName("Item 3 Points")
//    private String item3Points;
//
//    @ExcelCellName("Item 4")
//    private String item4;
//    @ExcelCellName("Item 4 Choices")
//    private String item4Choices;
//    @ExcelCellName("Item 4 Correct answer")
//    private String item4CorrectAnswer;
//    @ExcelCellName("Item 4 Standards")
//    private String item4Standards;
//    @ExcelCellName("Item 4 Points")
//    private String item4Points;
//
//    @ExcelCellName("Item 5")
//    private String item5;
//    @ExcelCellName("Item 5 Choices")
//    private String item5Choices;
//    @ExcelCellName("Item 5 Correct answer")
//    private String item5CorrectAnswer;
//    @ExcelCellName("Item 5 Standards")
//    private String item5Standards;
//    @ExcelCellName("Item 5 Points")
//    private String item5Points;
//
//    @ExcelCellName("Item 6")
//    private String item6;
//    @ExcelCellName("Item 6 Choices")
//    private String item6Choices;
//    @ExcelCellName("Item 6 correct answer")
//    private String item6CorrectAnswer;
//    @ExcelCellName("Item 6 Standards")
//    private String item6Standards;
//    @ExcelCellName("Item 6 Points")
//    private String item6Points;
//
//    @ExcelCellName("Item 7")
//    private String item7;
//    @ExcelCellName("Item 7 Choices")
//    private String item7Choices;
//    @ExcelCellName("Item 7 correct answer")
//    private String item7CorrectAnswer;
//    @ExcelCellName("Item 7 Standards")
//    private String item7Standards;
//    @ExcelCellName("Item 7 Points")
//    private String item7Points;
//
//    @ExcelCellName("Item 8")
//    private String item8;
//    @ExcelCellName("Item 8 Choices")
//    private String item8Choices;
//    @ExcelCellName("Item 8 Correct Answer")
//    private String item8CorrectAnswer;
//    @ExcelCellName("Item 8 Standards")
//    private String item8Standards;
//    @ExcelCellName("Item 8 Points")
//    private String item8Points;
//
//    @ExcelCellName("Item 9")
//    private String item9;
//    @ExcelCellName("Item 9 Choices")
//    private String item9Choices;
//    @ExcelCellName("Item 9 Correct Answer")
//    private String item9CorrectAnswer;
//    @ExcelCellName("Item 9 Standards")
//    private String item9Standards;
//    @ExcelCellName("Item 9 Points")
//    private String item9Points;
//
//    @ExcelCellName("Item 10")
//    private String item10;
//    @ExcelCellName("Item 10 Choices")
//    private String item10Choices;
//    @ExcelCellName("Item 10 Correct Answer")
//    private String item10CorrectAnswer;
//    @ExcelCellName("Standard")
//    private String standard;
//    @ExcelCellName("Item 10 Points")
//    private String item10Points;

    @ExcelUnknownCells
    private Map<String, String> unknownCells;

    public String getChoicePagePhoto() {
        return this.choicePagePhoto
            .replaceAll("<(/|)image>", "")
            .split("[.]")[0] + ".jpg";
    }

    public String getPassageBody() {
        return "";
    }

    public String getValue(String key) {
        return this.unknownCells.keySet()
            .stream()
            .filter(_key -> _key.equalsIgnoreCase(key))
            .map(_key -> this.unknownCells.get(_key))
            .collect(Collectors.joining());
    }
}
