package com.nixagh.contentinput.modal.excel.GT;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nghia.nguyen-dinh
 * @since 11/7/2023 at 1:52 PM
 */
@Getter
@Setter
@ExcelSheet("WordTies")
public class WordTieSheet {
    /*Step	Item Type 	Direction Line 	Word ID	Word	Priority/Challenge	Item 	Answer Choices 	Correct Answers	Correct emoji with  feedback phrases randomly selected. 	 Incorrect Feedback 1 to use when all correct answers are chosen on first try but also at least one incorrect is chosen. Students do not get a second try. 	Incorrect emoji with growth mindset feedback. 	Incorrect Feedback 1 to use when none of the student's choices on first try were correct.	Incorrect Feedback 1 to use when not all of the correct answers were chosen on the first try. This is also for when the student chooses only one answer, and it is correct.   	"Incorrect emoji with Feedback phrases randomly selected.
"	"Final incorrect feedback for students who had a second try.

"	Thinking emoji and Show Me button	Standard	Points
*/
    @ExcelCellName("Step")
    private Integer step;
    @ExcelCellName("Item Type")
    private String itemType;
    private String directionLine = "<i>Select the words or phrases that best complete the sentence or answer the question. Select all that apply.</i>";
    @ExcelCellName("Word ID")
    private String wordID;
    @ExcelCellName("Word")
    private String word;
    @ExcelCellName("Priority/Challenge")
    private String priorityChallenge;
    @ExcelCellName("Item")
    private String item;
    @ExcelCellName("Answer Choices")
    private String answerChoices;
    @ExcelCellName("Correct Answers")
    private String correctAnswers;
    @ExcelCellName("Correct emoji with feedback phrases randomly selected.")
    private String correctEmoji = "[\"Way to go!\",\"Great job!\",\"You did it!\",\"Terrific!\"]";

    @ExcelCellName("Incorrect Feedback 1")
    private String incorrectFeedback1;
    @ExcelCellName("Incorrect Feedback 2")
    private String incorrectFeedback2;
    @ExcelCellName("Incorrect Feedback 3")
    private String incorrectFeedback3;
    @ExcelCellName("Final incorrect feedback for students who had a second try.")
    private String incorrectFeedback4;

    private String incorrectEmoji1 = "[\"Nice try!\"]";
    private String incorrectEmoji2 = "[\"You can do this!\",\"Keep practicing.\",\"Keep trying.\",\"Nice try!\",\"Try another way.\"]";
    private String incorrectEmoji3 = "[\"You can do this!\",\"Keep practicing.\",\"Keep trying.\",\"Nice try!\",\"Try another way.\"]";
    private String incorrectEmoji4 = "[\"Nice try!\"]";
    @ExcelCellName("Standard")
    private String standard;
    @ExcelCellName("Points")
    private String points;
}