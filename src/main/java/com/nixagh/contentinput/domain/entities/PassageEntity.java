package com.nixagh.contentinput.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;


/**
 * @author nghia.nguyen-dinh
 * @since 11/6/2023 at 1:12 PM
 */
@Getter
@Setter
@Entity
@Table(name = "passage")
public class PassageEntity {
    // passageid, passageuid, passagecontent, "name", resourceid, productid, scramble, passagenumber, passageaudio, originalitemid, adjacentpassagename, "section", choicepassage, passagesummary, directionline
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passageid")
    private Long passageId;
    @Column(name = "passageuid")
    private String passageUid;
    @Column(name = "passagecontent")
    private String passageContent;
    @Column(name = "name")
    private String name;
    @Column(name = "resourceid")
    private BigInteger resourceId;
    @Column(name = "productid")
    private BigInteger productId;
    @Column(name = "scramble")
    private boolean scramble;

    @Column(name = "passagenumber", unique = true)
    private Long passageNumber;
    @Column(name = "passageaudio")
    private String passageAudio;
    @Column(name = "originalitemid")
    private Long originalItemId;
    @Column(name = "adjacentpassagename")
    private String adjacentPassageName;
    @Column(name = "section")
    private String section;
    @Column(name = "choicepassage")
    private Boolean choicePassage;
    @Column(name = "passagesummary")
    private String passageSummary;
    @Column(name = "directionline")
    private String directionLine;
}