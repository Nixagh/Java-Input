package com.nixagh.contentinput.modal.excel;

import com.nixagh.contentinput.common.excelFomat.WordListSheetFormat;
import com.poiji.annotation.ExcelCellName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nghia.nguyen-dinh
 * @since 11/2/2023 at 1:12 PM
 */
@Getter
@Setter
public class WordListSheet {
    @ExcelCellName("WordID")
    private String wordID;
    @ExcelCellName("Level")
    private String level;
    @ExcelCellName("Unit")
    private Integer unit;
    @ExcelCellName("Multiple-Meaning")
    private String multipleMeaning;
    @ExcelCellName("WJ Definitions and WJ Sentence entry fields")
    private Integer wjDefinitionsAndWjSentenceEntryFields;
    @ExcelCellName("Priority/Challenge")
    private String priorityChallenge;
    @ExcelCellName("Word")
    private String word;
    @ExcelCellName("Themes")
    private String themes;
    @ExcelCellName("Pronunciation")
    private String pronunciation;
    @ExcelCellName("Part of Speech")
    private String partOfSpeech;
    @ExcelCellName("Feedback Rollover DefinitionSheet")
    private String feedbackRolloverDefinition;
    @ExcelCellName("Synonyms")
    private String synonyms;
    @ExcelCellName("Antonyms")
    private String antonyms;
    @ExcelCellName("P1 Set")
    private String p1Set;
    @ExcelCellName("P2 Set")
    private String p2Set;
    @ExcelCellName("Tier")
    private Integer tier;

    @Override
    public String toString() {
        return """
            {"wordID": "%s","level": "%s","unit": %d,"multipleMeaning": "%s","wjDefinitionsAndWjSentenceEntryFields": %d,"priorityChallenge": "%s","word": "%s","themes": "%s","pronunciation": "%s","partOfSpeech": "%s","feedbackRolloverDefinition": "%s","synonyms": "%s","antonyms": "%s","p1Set": "%s","p2Set": "%s","tier": %d}
            """.formatted(this.wordID, this.level, this.unit, this.multipleMeaning, this.wjDefinitionsAndWjSentenceEntryFields, this.priorityChallenge, this.word, this.themes, this.pronunciation, this.partOfSpeech, this.feedbackRolloverDefinition, this.synonyms, this.antonyms, this.p1Set, this.p2Set, this.tier);
    }
}