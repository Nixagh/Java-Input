package com.nixagh.contentinput.util;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        var sheetNameFound = this.getSheetName(path, sheetName);
        var options = getPoijiOptions(sheetNameFound);
        return Poiji.fromExcel(excel, clazz, options);
    }

    public String getSheetName(String path, String name) {
        return this.getSheetNames(path).stream()
            .filter(sheetName -> sheetName.toLowerCase().contains(name.toLowerCase()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Sheet name not found"));
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

    public Workbook openFile(String filePath){
        try {
            //reading data from a file in the form of bytes
            FileInputStream fis = new FileInputStream(filePath);
            //constructs an XSSFWorkbook object, by buffering the whole stream into the memory
            return new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getListThemeSheetName(String path) {
        String searchValue = "theme";
        return this.getSheetNames(path).stream()
            .filter(sheetName -> sheetName.toLowerCase().contains(searchValue))
            .collect(Collectors.toList());
    }

    public List<String> getSheetNames(String path) {
        Workbook wb = this.openFile(path);
        return IntStream.range(0, wb.getNumberOfSheets())
            .mapToObj(wb::getSheetName)
            .collect(Collectors.toList());
    }
}
