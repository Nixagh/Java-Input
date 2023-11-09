package com.nixagh.contentinput.domain.model.excel.SP;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nghia.nguyen-dinh
 * @since 11/6/2023 at 8:57 AM
 */
@Getter
@Setter
@ExcelSheet("WordStudy")
public class WordStudySheet {
    //Step 	Word ID	Multiple Meaning	Word	Pronunciation	Part of Speech	Inflected Form	Characteristic Image	Prefix	Root or Base	Suffix	Introduction	Sample Sentence 1	Sample Sentence 1 Explanation	Image Details	Sample Sentence 2	Sample Sentence 2 Explanation	Image Details	Word Journal Prompt	Standard
    @ExcelCellName("Step")
    private Integer step;
    @ExcelCellName("Word ID")
    private String wordID;
    @ExcelCellName("Multiple Meaning")
    private String multipleMeaning;
    @ExcelCellName("Word")
    private String word;
    @ExcelCellName("Pronunciation")
    private String pronunciation;
    @ExcelCellName("Part of Speech")
    private String partOfSpeech;
    @ExcelCellName("Inflected Form")
    private String inflectedForm;
    @ExcelCellName("Characteristic Image")
    private String characteristicImage;
    @ExcelCellName("Prefix")
    private String prefix;
    @ExcelCellName("Root or Base")
    private String rootOrBase;
    @ExcelCellName("Suffix")
    private String suffix;
    @ExcelCellName("Introduction")
    private String introduction;
    @ExcelCellName("Sample Sentence 1")
    private String sampleSentence1;
    @ExcelCellName("Sample Sentence 1 Explanation")
    private String sampleSentence1Explanation;
    @ExcelCellName("Image Details")
    private String imageDetails;
    @ExcelCellName("Sample Sentence 2")
    private String sampleSentence2;
    @ExcelCellName("Sample Sentence 2 Explanation")
    private String sampleSentence2Explanation;
    @ExcelCellName("Word Journal Prompt")
    private String wordJournalPrompt;
    @ExcelCellName("Standard")
    private String standard;
}
