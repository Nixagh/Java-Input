package com.nixagh.contentinput.service;

import com.nixagh.contentinput.common.enums.CorrectAnswer;
import com.nixagh.contentinput.domain.entities.PassageEntity;
import com.nixagh.contentinput.domain.entities.QuestionEntity;
import com.nixagh.contentinput.domain.model.PassageTab;
import com.nixagh.contentinput.domain.model.QuestionContentTab;
import com.nixagh.contentinput.domain.model.QuestionTab;
import com.nixagh.contentinput.domain.model.excel.WordListSheet;
import com.nixagh.contentinput.domain.repository.PassageRepository;
import com.nixagh.contentinput.domain.repository.QuestionRepository;
import com.nixagh.contentinput.util.ExcelReader;
import javax.persistence.EntityManager;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author nghia.nguyen-dinh
 * @since 11/6/2023 at 8:58 AM
 */

@Getter
@Setter
@Slf4j
@Component
public class VWABaseService {
    protected List<WordListSheet> wordListSheets;
    protected List<QuestionEntity> questions = new ArrayList<>();
    protected Map<String, List<String>> error = new HashMap<>();
    protected List<String> logs = new ArrayList<>();

    protected String wordListSheetName = "wordList";

    protected boolean autoScore = true;
    protected Integer questionTypeId = 49;

    protected String path;
    protected BigInteger productId;
    protected BigInteger resourceId;
    protected String resourceCode;
    protected String productCode;
    protected int unit;

    protected final ExcelReader excelReader;
    protected final QuestionRepository questionRepository;
    protected final PassageRepository passageRepository;
    protected final EntityManager entityManager;
//    protected final VelocityEngine velocityEngine;
    protected final VelocityEngine velocityEngine = new VelocityEngine();


    public VWABaseService(ExcelReader excelReader, QuestionRepository questionRepository, PassageRepository passageRepository, EntityManager entityManager) {
        this.excelReader = excelReader;
        this.questionRepository = questionRepository;
        this.passageRepository = passageRepository;
        this.entityManager = entityManager;
    }

    public void init() {
        this.wordListSheets = this.excelReader.getExcelFile(this.path, this.wordListSheetName, WordListSheet.class);
    }

    public BigInteger getProductId() {
        if (this.productId == null) {
            var query = this.entityManager.createNativeQuery("SELECT productid FROM product WHERE code = ?");
            query.setParameter(1, this.productCode);
            this.productId = (BigInteger) query.getSingleResult();
        }
        return this.productId;
    }

    public BigInteger getResourceId() {
        if (this.resourceId == null) {
            var query = this.entityManager.createNativeQuery("SELECT resourceid FROM resource WHERE resourcecode = ?");
            query.setParameter(1, this.resourceCode);
            this.resourceId = (BigInteger) query.getSingleResult();
        }
        return this.resourceId;
    }

    protected List<PassageEntity> getPassages(int questionNumber) {
        return this.passageRepository.getPassageByQuestionNumberAndResourceId(questionNumber, this.resourceId);
    }

    public String getType() {
        return "Vocabulary";
    }

    public Integer getAdaptiveAnswerCount() {
        return 0;
    }

    public void saveQuestion() {
        this.questionRepository.saveAll(this.questions);
        this.createLog(this.logs, this.error.isEmpty());
    }

    public void createLog() {
        this.createLog(this.logs, this.error.isEmpty());
    }

    public void addError(String key, String value) {
        if (this.error.containsKey(key)) {
            this.error.get(key).add(value);
        } else {
            var list = new ArrayList<String>();
            list.add(value);
            this.error.put(key, list);
        }
    }

    private void createLog(List<String> logs, boolean status) {
        // add log to file
        var logPath = "src/main/resources/" + this.getResourceCode() + "/" + this.getUnit() + "/" + this.getType() + ".log";
        var log = LogFactory.getLog(logPath);

        logs.forEach(log::info);
        if (!status) {
            this.error.forEach((key, value) -> {
                log.error(key);
                value.forEach(log::error);
            });
        } else {
            log.info("Total: " + logs.size());
            log.info("Success");
        }

        // clear log
        logs.clear();
        this.error.clear();
    }

    public String getError(String message) {
        return "Resource: " + this.getResourceCode() + " with Error: " + message;
    }

    public void createQuestion(){};

    public void createQuestion(QuestionTab questionTab, PassageTab passageTab, QuestionContentTab questionContentTab, String feedback) {
        var question = new QuestionEntity();
        question.setQuestionuid(String.valueOf(UUID.randomUUID()));
        question.setProductid(this.getProductId());
        question.setResourceid(this.getResourceId());

        // question tab
        this.setQuestionTab(question, questionTab);

        // passage tab
        if (this.getSetPassage()) this.setPassageTab(question, passageTab);

        // question content tab
        this.setQuestionContentTab(question, questionContentTab);

        // feedback tab
        this.setFeedbackTab(question, feedback);

        this.questions.add(question);

        // save question
//        this.questionRepository.save(question);
        System.out.println(question);
        this.logs.add("Create question success: " + question.getQuestionuid());
    }

    public <T> void createQuestion(String wordId, String standard, String content, String correctAnswer, T sheet, int index) {
        try {
            var wordListSheet = this.getWordList(wordId);

            if (wordListSheet == null) {
                System.out.println("word id: " + wordId + " not found in word list" + " at item: " + index + " of definition");
                return;
            }

            var questionTab = this.buildQuestionTab(wordListSheet, standard, index);
            var passageTab = this.buildPassageTab(wordListSheet, sheet, index);
            var questionContentTab = this.buildQuestionContentTab(content, correctAnswer, index);
            var feedbackTab = this.buildFeedbackTab(wordListSheet, sheet, index);

            this.createQuestion(questionTab, passageTab, questionContentTab, feedbackTab);
        } catch (Exception e) {
            System.out.println(this.getError("wrong at item: " + index + " of definition"));
            e.printStackTrace();
        }
    }

    protected WordListSheet getWordList(String wordId) {
        return this.wordListSheets.stream()
            .filter(word -> word.getWordID().equals(wordId))
            .findFirst()
            .orElse(null);
    }

    public <T> void createQuestion(String wordId, String standard, String content, String correctAnswer, PassageTab passageTab, T sheet, int index) {
        try {
            var wordListSheet = this.wordListSheets.stream()
                .filter(word -> word.getWordID().equals(wordId))
                .findFirst()
                .orElse(null);

            if (wordListSheet == null) {
                System.out.println("word id: " + wordId + " not found in word list" + " at item: " + index + " of definition");
                return;
            }

            var questionTab = this.buildQuestionTab(wordListSheet, standard, index);

//            var passageTab = this.buildPassageTab(wordListSheet, sheet, index);

            var questionContentTab = this.buildQuestionContentTab(content, correctAnswer, index);
            var feedbackTab = this.buildFeedbackTab(wordListSheet, sheet, index);

            this.createQuestion(questionTab, passageTab, questionContentTab, feedbackTab);
        } catch (Exception e) {
            System.out.println(this.getError("wrong at item: " + index + " of definition"));
            e.printStackTrace();
        }
    }

    private void setQuestionTab(QuestionEntity question, QuestionTab questionTab) {
        question.setQuestionnumber(questionTab.getQuestionNumber());
        question.setQuestiontypeid(questionTab.getQuestionTypeId());
        question.setStandards(questionTab.getStandard());
        question.setAutoscorete(questionTab.isAutoScore());
        question.setLinktoquestion(questionTab.getLinkToQuestion());
        question.setWordid(questionTab.getWordId());
        question.setPathwayset1(questionTab.getPathwaySet1());
        question.setPathwayset2(questionTab.getPathwaySet2());
        question.setAdaptiveanswercount(questionTab.getAdaptiveAnswerCount());
    }

    private void setPassageTab(QuestionEntity question, PassageTab passageTab) {
        Optional.ofNullable(passageTab.getPassageId()).ifPresentOrElse(
            question::setPassageid,
            () -> {
                // create new passage and set passage id
                var passage = new PassageEntity();
                var uid = String.valueOf(UUID.randomUUID());
                passage.setPassageUid(uid);
                passage.setPassageContent(passageTab.getContent());

                var name = "Passage " + uid;

                passage.setName(name);
                passage.setResourceId(this.getResourceId());
                passage.setProductId(this.getProductId());

                var passageNumber = this.passageRepository.getLastPassageNumber();
                passage.setPassageNumber(passageNumber + 1);

                passage.setScramble(passageTab.isScramble());
                passage.setChoicePassage(passageTab.isChoicePassage());

                passage.setPassageSummary(passageTab.getSummary());
                passage.setDirectionLine(passageTab.getDirectionLine());

//                var result = this.passageRepository.save(passage);
//                question.setPassageid(result.getPassageId());
            }
        );
    }

    private void setQuestionContentTab(QuestionEntity question, QuestionContentTab questionContentTab) {
        question.setQuestionxml(this.getQuestionXML(questionContentTab.getContent()));
        question.setCorrectanswer(questionContentTab.getCorrectAnswer());
        question.setCorrectanswertexthtml(questionContentTab.getCorrectAnswerTextHtml());
    }

    private void setFeedbackTab(QuestionEntity question, String feedback) {
        question.setFeedback(feedback);
    }

    protected String convertFromVMFile(String template, Map<String, Object> map) {
        var context = new VelocityContext(map);
        var writer = new StringWriter();
        var _template = this.velocityEngine.getTemplate(template);

        _template.merge(context, writer);
        return writer.toString();
    }

    private String getQuestionXML(String questionContent) {
        Map<String, Object> map = Map.of("questionContent", questionContent);
        return this.convertFromVMFile("src/main/java/com/nixagh/contentinput/libs/Vm/QuestionXML.vm", map)
            .replaceAll("\n", "")
            .replaceAll("\r", "")
            .replaceAll("\t", "")
            .replaceAll(" +", " ");
    }

    protected String getCID(int questionNumber) {
        return String.format("%s_%s_u%s_q%s_ans01", this.productCode, this.getType(), this.parseQuestionNumber(this.getUnit()), this.parseQuestionNumber(questionNumber));
    }

    public String parseQuestionNumber(Integer questionNumber) {
        return questionNumber < 10 ? "0" + questionNumber : questionNumber.toString();
    }

    protected QuestionTab buildQuestionTab(WordListSheet wordListSheet, String standard, int questionNumber) {
        var questionTab = new QuestionTab();

        questionTab.setQuestionNumber(questionNumber);
        questionTab.setStandard(standard);
        questionTab.setWordId(wordListSheet.getWordID());
        questionTab.setPathwaySet1(wordListSheet.getP1Set());
        questionTab.setPathwaySet2(wordListSheet.getP2Set());

        questionTab.setAutoScore(this.isAutoScore());
        questionTab.setQuestionTypeId(this.getQuestionTypeId());
        questionTab.setAdaptiveAnswerCount(this.getAdaptiveAnswerCount());

        return questionTab;
    }

    protected QuestionContentTab buildQuestionContentTab(String content, String correctAnswer, int questionNumber) {
        var questionContentTab = new QuestionContentTab();

        questionContentTab.setContent(content);

        var comp = CorrectAnswer.compMap.get(this.getType());
        questionContentTab.setCorrectAnswer(this.getCID(questionNumber), correctAnswer, comp.getType(), comp.getSubtype());
        questionContentTab.setCorrectAnswerTextHtml(correctAnswer);

        return questionContentTab;
    }

    protected <T> String getCorrectAnswerValue(T sheet) {
        return "";
    }

    protected <T> PassageTab buildPassageTab(WordListSheet wordListSheet, T sheet, int questionNumber) {
        return new PassageTab();
    }

    protected <T> String buildFeedbackTab(WordListSheet wordListSheet, T sheet, int questionNumber) {
        return "";
    }

    protected boolean getSetPassage() {
        return true;
    }

    protected Matcher getMatcher(String regex, String input) {
        return Pattern.compile(regex).matcher(input);
    }

    protected String convertAtFeedBack(String feedback) {
        var regex = "<b|((word|)?<id>\\d+)>(?<word>.+?)<(/|)(b|(word|)\\d+)>";
        var matcher = this.getMatcher(regex, feedback);

        if(!matcher.find()) return feedback;

        var wordId = matcher.group("id");
        var word = matcher.group("word");

        // template: "Connected" defines the word <4021>cohesive</word4021>.
        // result: "Connected" defines the word <4021>word4021:cohesive</word4021>.
        var replacement = String.format("<word%s>word%s:%s</word%s>", wordId, wordId, word, wordId);
        return feedback.replaceAll(regex, replacement);
    }

    protected String passageBodyConvert(String content) {
        // remove title
        var regex = "<title>(.+?)</title>";
        var matcher = this.getMatcher(regex, content);

        if(matcher.find()) {
            content = content.replaceAll(regex, "");
        }

        // remove extra space
        content = content.replaceAll(" +", " ");

        // convert <paragraph id="1"> to <div class="paragraph" id="1">
        regex = "<paragraph(\\s+)(id|ID|)(\\s+|)=(\\s+|)(\"|)(?<id>\\d+)(\"|)>";
        matcher = this.getMatcher(regex, content);

        while (matcher.find()) {
            content = content.replaceAll(regex, "<div class=\"paragraph\" id=\"$1\">");
        }

        // convert </paragraph> to </div>
        regex = "</paragraph(\\s+)(id|ID|)(\\s+|)=(\\s+|)\\d+>";
        matcher = this.getMatcher(regex, content);

        while (matcher.find()) {
            content = content.replaceAll(regex, "</div>");
        }

        // <word4104>voluminous</word4104> -> <word4104>word4104:voluminous</word4104>
        regex = "<word(?<id>\\d+)>(?<word>.+?)</word\\k<id>>";
        matcher = this.getMatcher(regex, content);

        while (matcher.find()) {
            var wordId = matcher.group("id");
            var word = matcher.group("word");

            // template: "Connected" defines the word <4021>cohesive</word4021>.
            // result: "Connected" defines the word <4021>word4021:cohesive</word4021>.
            var replacement = String.format("<word%s>word%s:%s</word%s>", wordId, wordId, word, wordId);
            content = content.replace(regex, replacement);
        }

        return content;
    }

}
