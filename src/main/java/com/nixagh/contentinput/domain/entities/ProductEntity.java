package com.nixagh.contentinput.domain.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity {
    //code, "name", programid, subjectid, productlevelid, gradeid, seatproductcode, publishstate, coverimage, headerimage, uselevel, editiontype, linktoproductid, createddate, modifieddate, assetfolder, coverimage2x, headerimage2x, accesscode, producttypeid, resourcetemplateid, originalproduct, originalproductbytoc, originalitemid, haswordlist, singlequestionmode, bulkgrading, multilanguage, languages, useintegratedmenustructure, productbrand, linktoproductcodes, shareacrossprogramseries, fullaccessenabled, ebookuri, ebookcoverimage, filesize, offlinebook, chapters, pages, alternativetitle, adocreportenabled, audioassessment, jsoninfor, pocketchartvalue, sharedresourceid

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    private Long productid;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "programid")
    private Long programid;

    @Column(name = "subjectid")
    private Integer subjectid;

    @Column(name = "productlevelid")
    private Integer productlevelid;

    @Column(name = "gradeid")
    private Integer gradeid;

    @Column(name = "seatproductcode")
    private String seatproductcode;

    @Column(name = "publishstate")
    private String publishstate;

    @Column(name = "coverimage")
    private String coverimage;

    @Column(name = "headerimage")
    private String headerimage;

    @Column(name = "uselevel")
    private Boolean uselevel;

    @Column(name = "editiontype")
    private String editiontype;

    @Column(name = "linktoproductid")
    private Integer linktoproductid;

    @Column(name = "createddate")
    private Timestamp createddate = new Timestamp(System.currentTimeMillis());

    @Column(name = "modifieddate")
    private Timestamp modifieddate;

    @Column(name = "assetfolder")
    private String assetfolder;

    @Column(name = "coverimage2x")
    private String coverimage2x;

    @Column(name = "headerimage2x")
    private String headerimage2x;

    @Column(name = "accesscode")
    private String accesscode;

    @Column(name = "producttypeid")
    private Integer producttypeid;

    @Column(name = "resourcetemplateid")
    private Integer resourcetemplateid;

    @Column(name = "originalproduct")
    private String originalproduct;

    @Column(name = "originalproductbytoc")
    private String originalproductbytoc;

    @Column(name = "originalitemid")
    private Integer originalitemid;

    @Column(name = "haswordlist")
    private Boolean haswordlist;

    @Column(name = "singlequestionmode")
    private Boolean singlequestionmode;

    @Column(name = "bulkgrading")
    private Boolean bulkgrading;

    @Column(name = "multilanguage")
    private Boolean multilanguage;

    @Column(name = "languages")
    private String languages;

    @Column(name = "useintegratedmenustructure")
    private Boolean useintegratedmenustructure;

    @Column(name = "productbrand")
    private String productbrand;

    @Column(name = "linktoproductcodes")
    private String linktoproductcodes;

    @Column(name = "shareacrossprogramseries")
    private Boolean shareacrossprogramseries;

    @Column(name = "fullaccessenabled")
    private Boolean fullaccessenabled;

    @Column(name = "ebookuri")
    private String ebookuri;

    @Column(name = "ebookcoverimage")
    private String ebookcoverimage;

    @Column(name = "filesize")
    private Integer filesize;

    @Column(name = "offlinebook")
    private Boolean offlinebook;

    @Column(name = "chapters")
    private Integer chapters;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "alternativetitle")
    private String alternativetitle;

    @Column(name = "adocreportenabled")
    private Boolean adocreportenabled;

    @Column(name = "audioassessment")
    private Boolean audioassessment;

    @Column(name = "jsoninfor")
    private String jsoninfor;

    @Column(name = "pocketchartvalue")
    private String pocketchartvalue;

    @Column(name = "sharedresourceid")
    private Integer sharedresourceid;

}
