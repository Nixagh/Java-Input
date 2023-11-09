package com.nixagh.contentinput.util;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * @author nghia.nguyen-dinh
 * @since 11/2/2023 at 1:07 PM
 */
@Component
@RequiredArgsConstructor
public class ExcelReader {
    private final FileUtil fileUtil;

    public <T> List<T> getExcelFile(String path, String sheetName, Class<T> clazz) {
        File excel = fileUtil.getFile(path);

        var options = getPoijiOptions(sheetName);
        return Poiji.fromExcel(excel, clazz, options);
    }

    public <T> List<T> getExcelFile(String path, Class<T> clazz) {
        File excel = fileUtil.getFile(path);
        var options = getPoijiOptions();
        return Poiji.fromExcel(excel, clazz, options);
    }

    private PoijiOptions getPoijiOptions() {
        return PoijiOptions.PoijiOptionsBuilder.settings()
            .headerStart(0)
            .trimCellValue(true)
            .build();
    }

    private PoijiOptions getPoijiOptions(String sheetName) {
        return PoijiOptions.PoijiOptionsBuilder.settings()
            .sheetName(sheetName)
            .headerStart(0)
            .trimCellValue(true)
            .build();
    }


    private List<String> getHeader(String path, String sheetName) {
        File excel = fileUtil.getFile(path);

        var options = PoijiOptions.PoijiOptionsBuilder.settings()
            .sheetName(sheetName)
            .trimCellValue(true)
            .build();
        return Poiji.fromExcel(excel, String.class, options);
    }
}