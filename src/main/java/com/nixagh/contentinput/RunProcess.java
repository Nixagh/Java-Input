package com.nixagh.contentinput;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nixagh.contentinput.domain.repository.PassageRepository;
import com.nixagh.contentinput.domain.repository.QuestionRepository;
import com.nixagh.contentinput.domain.repository.ResourceRepository;
import com.nixagh.contentinput.service.IP.GT.ChoosingRightWordGTService;
import com.nixagh.contentinput.service.IP.GT.ExampleService;
import com.nixagh.contentinput.service.IP.GT.VocabularyInContextDiffService;
import com.nixagh.contentinput.service.IP.GT.VocabularyInContextOnLVService;
import com.nixagh.contentinput.service.IP.GT.WordTieService;
import com.nixagh.contentinput.service.IP.OYO.AdaptivePracticeService;
import com.nixagh.contentinput.service.IP.OYO.ChoosingRightWordOYOService;
import com.nixagh.contentinput.service.IP.OYO.CompletingTheSentenceService;
import com.nixagh.contentinput.service.IP.OYO.DifferentiatedPassage1;
import com.nixagh.contentinput.service.IP.OYO.DifferentiatedPassage2;
import com.nixagh.contentinput.service.IP.OYO.OnLVPassage1;
import com.nixagh.contentinput.service.IP.OYO.OnLVPassage2;
import com.nixagh.contentinput.service.IP.SP.DefinitionService;
import com.nixagh.contentinput.service.IP.SP.VisualService;
import com.nixagh.contentinput.service.IP.SP.WordStudyService;
import com.nixagh.contentinput.service.VWABaseService;
import com.nixagh.contentinput.util.ExcelReader;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.io.File;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author nghia.nguyen-dinh
 * @since 11/7/2023 at 11:49 AM
 */
@RequiredArgsConstructor
@Setter
@Slf4j
@Component
public class RunProcess {
    private final EntityManager entityManager;
    private final QuestionRepository questionRepository;
    private final PassageRepository passageRepository;
    private final ResourceRepository resourceRepository;
    private final ExcelReader excelReader;
    private final ObjectMapper objectMapper;

    private String folder;

    public void setFolder(String folder) {
        this.folder = folder;
    }

    private final Map<String, VWABaseService> processMap = new HashMap<>();
    private final Map<String, String> typeMap = new HashMap<>();

    public void init() {
        typeMap.put("GT", "Guided Tour");
        typeMap.put("SP", "Starting Point");
        typeMap.put("OYO", "On Your Own");

        /*
            Choosing the Right Word                 - Guided Tour
            Vocabulary in Context (On-level)        - Guided Tour
            Vocabulary in Context (Differentiated)  - Guided Tour
            Word Ties                               - Guided Tour
            Example/Nonexample                      - Guided Tour
            Adaptive Practice                       - On Your Own
            Choosing the Right Word                 - On Your Own
            On-level Passage 1                      - On Your Own
            On-level Passage 2                      - On Your Own
            Differentiated Passage 1                - On Your Own
            Differentiated Passage 2                - On Your Own
            Completing the Sentence                 - On Your Own
            Definitions                             - Starting Point
            Visuals                                 - Starting Point
            Word Study                              - Starting Point
        */
        processMap.put("Definitions-Starting Point", new DefinitionService(excelReader, questionRepository, passageRepository, entityManager));
        processMap.put("Visuals-Starting Point", new VisualService(excelReader, questionRepository, passageRepository, entityManager));
        processMap.put("Word Study-Starting Point", new WordStudyService(excelReader, questionRepository, passageRepository, entityManager));
        processMap.put("Choosing the Right Word-Guided Tour", new ChoosingRightWordGTService(excelReader, questionRepository, passageRepository, entityManager));
        processMap.put("Vocabulary in Context (On-level)-Guided Tour", new VocabularyInContextOnLVService(excelReader, questionRepository, passageRepository, entityManager));
        processMap.put("Vocabulary in Context (Differentiated)-Guided Tour", new VocabularyInContextDiffService(excelReader, questionRepository, passageRepository, entityManager));
        processMap.put("Word Ties-Guided Tour", new WordTieService(excelReader, questionRepository, passageRepository, entityManager));
        processMap.put("Example/Nonexample-Guided Tour", new ExampleService(excelReader, questionRepository, passageRepository, entityManager));
        processMap.put("Adaptive Practice-On Your Own", new AdaptivePracticeService(excelReader, questionRepository, passageRepository, entityManager));
        processMap.put("Choosing the Right Word-On Your Own", new ChoosingRightWordOYOService(excelReader, questionRepository, passageRepository, entityManager));
        processMap.put("On-level Passage 1-On Your Own", new OnLVPassage1(excelReader, questionRepository, passageRepository, entityManager));
        processMap.put("On-level Passage 2-On Your Own", new OnLVPassage2(excelReader, questionRepository, passageRepository, entityManager));
        processMap.put("Differentiated Passage 1-On Your Own", new DifferentiatedPassage1(excelReader, questionRepository, passageRepository, entityManager));
        processMap.put("Differentiated Passage 2-On Your Own", new DifferentiatedPassage2(excelReader, questionRepository, passageRepository, entityManager));
        processMap.put("Completing the Sentence-On Your Own", new CompletingTheSentenceService(excelReader, questionRepository, passageRepository, entityManager));
    }

    private String getProductCode() {
        return this.getLastElementSplitBy(this.folder, "_");
    }

    private String getCategoryName(String category) {
        return this.getLastElementSplitBy(category, "_");
    }

    private Integer getUnitNumber(String unit) {
        var regex = "_U(?<unit>\\d)_";
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(unit);

        return Integer.parseInt(matcher.find() ? matcher.group("unit") : "0");
    }

    private String getLastElementSplitBy(String str, String splitBy) {
        var split = str.split(splitBy);
        return split[split.length - 1];
    }

    public void run() {
        var category = new File(folder).list();
        if ( category != null) {
            log.info("Run process for product: {}", this.getProductCode());
            Arrays.stream(category).forEach(this::runCategory);
        } else {
            log.info("No File");
        }
    }

    private void runCategory(String category) {
        var units = new File(folder + "\\" + category).list();
        assert units != null;
        // get category name
        var categoryName = this.getCategoryName(category);
        log.info("Run process for category: {}", categoryName);
        // run all unit
        Arrays.stream(units)
            .map(unit -> folder + "\\" + category + "\\" + unit)
            .filter(unit -> !unit.contains("~$VWA_"))
            .forEach(unit -> this.runUnit(unit, this.typeMap.get(categoryName)));
        System.out.println("\n\n\n");
        log.info("====================================================");
    }

    private void runUnit(String unit, String categoryName) {
        var unitNumber = this.getUnitNumber(unit);
        // get all resource code by product code and unit
        var resourceCodes = this.resourceRepository.getResourceCodes(this.getProductCode(), "Unit " + unitNumber, categoryName);
        log.info("Run process for unit: {}", unitNumber);
        // run all resource code
        resourceCodes.forEach(resourceCode -> this.runResourceCode(resourceCode, unit, unitNumber));
    }

    private void runResourceCode(Tuple resourceCode, String path, int unitNumber) {
        var description = resourceCode.get("description", String.class);
        var type = resourceCode.get("adaptiveResourceType", String.class);
        var productId = resourceCode.get("productId", Long.class);
        var resourceId = resourceCode.get("resourceId", Long.class);
        var _resourceCode = resourceCode.get("resourceCode", String.class);
        log.info("Run process for resource code: {}, description: {}", _resourceCode, description);

        var key = description + "-" + type;

        // get process by category name
        var process = processMap.get(key);
        if (process == null) {
            log.info("Not found process for category: {}", key);
            return;
        }
        // set data for process
        process.setPath(path);
        process.setProductCode(this.getProductCode());
        process.setUnit(unitNumber);
        process.setResourceCode(_resourceCode);
        process.setProductId(productId);
        process.setResourceId(resourceId);

        process.init();

        process.createQuestion();

        process.createLog();
        System.out.println("\n");
    }

    public static void main(String[] args) {
        var regex = "<b>(?<word>.+?)</b>";
        var str = "\"Connected\" defines the word <b>cohesive</b>.\"Connected\" defines the word <b>cohesive2</b>.\"Connected\" defines the word <b>cohesive4</b>defines the word";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(str);

        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            var str2 = "sdad";
            var word = matcher.group("word");
            System.out.println(matcher);
            str = str.replace(matcher.group(), str2);
        }

        System.out.println(str);
    }
    public void ping() {
        log.info("Pong !");
    }
}
