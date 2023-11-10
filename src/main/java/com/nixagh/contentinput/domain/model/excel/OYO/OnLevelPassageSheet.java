package com.nixagh.contentinput.domain.model.excel.OYO;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nghia.nguyen-dinh
 * @since 11/9/2023 at 4:57 PM
 */
@Getter
@Setter
public class OnLevelPassageSheet extends PassageSheet{
    @ExcelCellName("On-Level Passage Body")
    private String passageBody;
}
