package com.nixagh.contentinput.entities;

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
public class Question {
    /*"questionid","questionuid","questionxml","maxscore","correctanswer","passageid","resourceid","productid","questionnumber","questiontypeid","rendertype","labelschema","standards","scramble","rubric","hidequestionlabel","epilog","hint","linktoquestion","correctanswertexthtml","programtoc","title","action","problemsolution","rubricrules","word","rubricid","rubrictitle","hideoptionlabel","scrambleoption","writeonlines","originalitemid","webdok","correctasis","questionnumcssstyle","questionnumcssclass","createddate","modifieddate","algorithym","renderrule","definitionword","connotation","reportingrule","hidelabelrule","questionxmltemp","converttomc4print","rendertype4print","correctanswer4print","wordmeaning","labelschemacustom","xmlcontent2017","scoringformula","screenid","screenelementid","componentgradingrules","autoscorete","questionxmlprint","correctanswerprint","correctanswertexthtmlprint","correctanswer4printtext","collaboration","wordmapcontent","wordmapuri","feedback","questiontext","ans1","ans2","ans3","ans4","ans5","disabled","wordid","adaptivefeedback","adaptivequestionxml","pathwayset1","pathwayset2","settype","adaptiveanswercount","questionxml_backup","correctanswer_bk","correctanswertexthtml_bk"*/
    // help me gen all field
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionid;
    private String questionuid;
    private String questionxml;
    private Integer maxscore = 1;
    private String correctanswer;
    @Column(name = "passageid")
    private Long passageid;
    private BigInteger resourceid;
    private BigInteger productid;
    private Integer questionnumber;
    private Integer questiontypeid;
    private String rendertype = null;
    private String labelschema = "AL";
    private String standards;
    private boolean scramble;
    private String rubric = "";
    private boolean hidequestionlabel = false;
    private String epilog = null;
    private String hint = null;
    private Integer linktoquestion;
    private String correctanswertexthtml;
    private Integer programtoc;
    private String title = null;
    private String action = null;
    private String problemsolution = "";
    private String rubricrules = "";
    private String word = "";
    private Integer rubricid;
    private String rubrictitle = "";
    private boolean hideoptionlabel = false;
    private boolean scrambleoption = false;
    private String writeonlines = "";
    private Integer originalitemid ;
    private String webdok;
    private boolean correctasis = false;
    private String questionnumcssstyle = "";
    private String questionnumcssclass = "";
    private Timestamp createddate = new Timestamp(System.currentTimeMillis());
    private Timestamp modifieddate;
    private String algorithym;
    private String renderrule;
    private String definitionword;
    private String connotation;
    private String reportingrule;
    private boolean hidelabelrule;
    private String questionxmltemp;
    private boolean converttomc4print;
    private String rendertype4print = "D";
    private String correctanswer4print;
    private String wordmeaning;
    private String labelschemacustom;
    private String xmlcontent2017;
    private String scoringformula;
    private Integer screenid;
    private Integer screenelementid;
    private String componentgradingrules;
    private boolean autoscorete;
    private String questionxmlprint;
    private String correctanswerprint;
    private String correctanswertexthtmlprint;
    private String correctanswer4printtext;
    private String collaboration;
    private String wordmapcontent;
    private String wordmapuri;
    private String feedback;
    private String questiontext;
    private String ans1;
    private String ans2;
    private String ans3;
    private String ans4;
    private String ans5;
    private boolean disabled;
    private String wordid;
    private String adaptivefeedback;
    private String adaptivequestionxml;
    private String pathwayset1;
    private String pathwayset2;
    private String settype;
    private Integer adaptiveanswercount;
    private String questionxml_backup;
    private String correctanswer_bk;

    public String generateInsertValue() {
        // loop all field
        Field[] fields = this.getClass().getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        for (Integer i = 0; i < fields.length; i++) {
            try {
                // if field name equal questionid, skip
                if (fields[i].getName().equals("questionid")) {
                    continue;
                }

                stringBuilder.append("\"");
                stringBuilder.append(fields[i].get(this));
                stringBuilder.append("\"");
                if (i < fields.length - 1) {
                    stringBuilder.append(",");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
