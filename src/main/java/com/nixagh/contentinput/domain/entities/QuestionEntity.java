package com.nixagh.contentinput.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author nghia.nguyen-dinh
 * @since 11/2/2023 at 10:30 AM
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question")
public class QuestionEntity {
    /*"questionid","questionuid","questionxml","maxscore","correctanswer","passageid","resourceid","productid","questionnumber","questiontypeid","rendertype","labelschema","standards","scramble","rubric","hidequestionlabel","epilog","hint","linktoquestion","correctanswertexthtml","programtoc","title","action","problemsolution","rubricrules","word","rubricid","rubrictitle","hideoptionlabel","scrambleoption","writeonlines","originalitemid","webdok","correctasis","questionnumcssstyle","questionnumcssclass","createddate","modifieddate","algorithym","renderrule","definitionword","connotation","reportingrule","hidelabelrule","questionxmltemp","converttomc4print","rendertype4print","correctanswer4print","wordmeaning","labelschemacustom","xmlcontent2017","scoringformula","screenid","screenelementid","componentgradingrules","autoscorete","questionxmlprint","correctanswerprint","correctanswertexthtmlprint","correctanswer4printtext","collaboration","wordmapcontent","wordmapuri","feedback","questiontext","ans1","ans2","ans3","ans4","ans5","disabled","wordid","adaptivefeedback","adaptivequestionxml","pathwayset1","pathwayset2","settype","adaptiveanswercount","questionxml_backup","correctanswer_bk","correctanswertexthtml_bk"*/
    // help me gen all field
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionid")
    private Long questionid;

    @Column(name = "questionuid")
    private String questionuid;

    @Column(name = "questionxml")
    private String questionxml;

    @Column(name = "maxscore")
    private Integer maxscore = 1;

    @Column(name = "correctanswer")
    private String correctanswer;

    @Column(name = "passageid")
    private Long passageid;

    @Column(name = "resourceid")
    private Long resourceid;

    @Column(name = "productid")
    private Long productid;

    @Column(name = "questionnumber")
    private Integer questionnumber;

    @Column(name = "questiontypeid")
    private Integer questiontypeid;

    @Column(name = "standards")
    private String standards;

    @Column(name = "scramble")
    private boolean scramble;

    @Column(name = "hidequestionlabel")
    private boolean hidequestionlabel = false;


    @Column(name = "linktoquestion")
    private Integer linktoquestion;

    @Column(name = "correctanswertexthtml")
    private String correctanswertexthtml;;

    @Column(name = "programtoc")
    private Integer programtocid;

    @Column(name = "title")
    private String title = null;

    @Column(name = "action")
    private String action = null;

    @Column(name = "word")
    private String word = "";

    @Column(name = "rubricid")
    private Integer rubricid;

    @Column(name = "rubrictitle")
    private String rubrictitle = "";

    @Column(name = "hideoptionlabel")
    private boolean hideoptionlabel = false;

    @Column(name = "scrambleoption")
    private boolean scrambleoption = false;

    @Column(name = "originalitemid")
    private Integer originalitemid ;

    @Column(name = "questionnumcssstyle")
    private String questionnumcssstyle = "";

    @Column(name = "questionnumcssclass")
    private String questionnumcssclass = "";

    @Column(name = "createddate")
    private Timestamp createddate = new Timestamp(System.currentTimeMillis());

    @Column(name = "modifieddate")
    private Timestamp modifieddate;

    @Column(name = "algorithym")
    private String algorithym;

    @Column(name = "renderrule")
    private String renderrule;

    @Column(name = "definitionword")
    private String definitionword;

    @Column(name = "connotation")
    private String connotation;

    @Column(name = "reportingrule")
    private String reportingrule;

    @Column(name = "hidelabelrule")
    private boolean hidelabelrule;

    @Column(name = "questionxmltemp")
    private String questionxmltemp;

    @Column(name = "correctanswerprint")
    private String rendertype4print = "D";

    @Column(name = "correctanswer4printtext")
    private String correctanswer4printtext;

    @Column(name = "correctanswer4print")
    private String correctanswer4print;

    @Column(name = "collaboration")
    private String collaboration;

    @Column(name = "wordmapcontent")
    private String wordmapcontent;

    @Column(name = "wordmapuri")
    private String wordmapuri;

    @Column(name = "feedback")
    private String feedback;

    @Column(name = "questiontext")
    private String questiontext;

    @Column(name = "ans1")
    private String ans1;

    @Column(name = "ans2")
    private String ans2;

    @Column(name = "ans3")
    private String ans3;

    @Column(name = "ans4")
    private String ans4;

    @Column(name = "ans5")
    private String ans5;

    @Column(name = "disabled")
    private boolean disabled;

    @Column(name = "wordid")
    private String wordid;

    @Column(name = "adaptivefeedback")
    private String adaptivefeedback;

    @Column(name = "adaptivequestionxml")
    private String adaptivequestionxml;

    @Column(name = "pathwayset1")
    private Integer pathwayset1;

    @Column(name = "pathwayset2")
    private Integer pathwayset2;

    @Column(name = "settype")
    private String settype;

    @Column(name = "adaptiveanswercount")
    private Integer adaptiveanswercount;

    @Column(name = "questionxml_backup")
    private String questionxml_backup;

    @Column(name = "correctanswer_bk")
    private String correctanswer_bk;

    @Column(name = "correctanswertexthtml_bk")
    private String correctanswertexthtml_bk;

}
