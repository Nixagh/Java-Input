package com.nixagh.contentinput.service.IP.SP;

import com.nixagh.contentinput.modal.excel.SP.DefinitionSheet;
import com.nixagh.contentinput.modal.excel.SP.VisualSheet;
import com.nixagh.contentinput.modal.excel.SP.WordStudySheet;
import com.nixagh.contentinput.repository.PassageRepository;
import com.nixagh.contentinput.repository.QuestionRepository;
import com.nixagh.contentinput.service.VWABaseService;
import com.nixagh.contentinput.util.ExcelReader;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author nghia.nguyen-dinh
 * @since 11/6/2023 at 8:55 AM
 */

@Getter
@Setter
@Component
public class SPService extends VWABaseService {
    protected List<DefinitionSheet> definitionSheets;
    protected List<VisualSheet> visualSheets;
    protected List<WordStudySheet> wordStudySheets;

    protected String definitionSheetName = "Definitions";
    protected String visualSheetName = "visual";
    protected String wordStudySheetName = "WordStudy";


    public SPService(ExcelReader excelReader, QuestionRepository questionRepository, PassageRepository passageRepository, EntityManager entityManager) {
        super(excelReader, questionRepository, passageRepository, entityManager);
    }

    public void init() {
        super.init();
        this.definitionSheets = this.excelReader.getExcelFile(this.path, this.definitionSheetName, DefinitionSheet.class);
        this.visualSheets = this.excelReader.getExcelFile(this.path, this.visualSheetName, VisualSheet.class);
        this.wordStudySheets = this.excelReader.getExcelFile(this.path, this.wordStudySheetName, WordStudySheet.class);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String getType() {
        return "SP";
    }
}
