package com.nixagh.contentinput.domain.model.excel.OYO;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nghia.nguyen-dinh
 * @since 11/8/2023 at 3:10 PM
 */
@ExcelSheet("DiffPsg")
@Getter
@Setter
public class DifferentiatedPassageSheet extends PassageSheet {
    @ExcelCellName("Differentiated Passage Body")
    private String passageBody;
}
