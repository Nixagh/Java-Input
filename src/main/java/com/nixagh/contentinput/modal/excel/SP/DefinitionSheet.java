package com.nixagh.contentinput.modal.excel.SP;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nghia.nguyen-dinh
 * @since 11/2/2023 at 1:28 PM
 */
@Getter
@Setter
@ExcelSheet("Definitions")
public class DefinitionSheet {
    //     Step	Item Type 	WordID	 Word	Pronunciation	Part of Speech	Inflected Forms Direction Line 	Inflected Forms	Definition 	Example Sentence	Synonyms	Antonyms	Correct Answer(s)	Check Your Spelling Flash Feedback	Word Journal Prompt Definitions	Instructional Video Pickup Code VWS22	Video Word Journal Prompt	Standard
    // help me gen all field
    @ExcelCellName("Step")
    private String step;
    @ExcelCellName("Item Type")
    private String itemType;
    @ExcelCellName("WordID")
    private String wordID;
    @ExcelCellName("Word")
    private String word;
    @ExcelCellName("Pronunciation")
    private String pronunciation;
    @ExcelCellName("Part of Speech")
    private String partOfSpeech;
    @ExcelCellName("Inflected Forms Direction Line")
    private String inflectedFormsDirectionLine;
    @ExcelCellName("Inflected Forms")
    private String inflectedForms;
    @ExcelCellName("Definition")
    private String definition;
    @ExcelCellName("Example Sentence")
    private String exampleSentence;
    @ExcelCellName("Synonyms")
    private String synonyms;
    @ExcelCellName("Antonyms")
    private String antonyms;
    @ExcelCellName("Correct Answer(s)")
    private String correctAnswers;
    @ExcelCellName("Check Your Spelling Flash Feedback")
    private String checkYourSpellingFlashFeedback;
    @ExcelCellName("Word Journal Prompt Definitions")
    private String wordJournalPromptDefinitions;
    @ExcelCellName("Instructional Video Pickup Code")
    private String instructionalVideoPickupCode;
    @ExcelCellName("Video Word Journal Prompt")
    private String videoWordJournalPrompt;
    @ExcelCellName("Standard")
    private String standard;
}
