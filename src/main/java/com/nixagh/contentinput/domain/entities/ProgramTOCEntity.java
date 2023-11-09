package com.nixagh.contentinput.domain.entities;

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
@Table(name = "programtoc")
public class ProgramTOCEntity {
//"name", description, hassharedresource, parentid, programid, displayorder, havebuildassessment, defaulttestbank, "language", linkid, nameontab, hide, lessons, showonlibrary, essentialquestions, nobreak, jsoninfor

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "programtocid")
    private Long programtocid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "hassharedresource")
    private Boolean hassharedresource;

    @Column(name = "parentid")
    private Integer parentid;

    @Column(name = "programid")
    private Integer programid;

    @Column(name = "displayorder")
    private Integer displayorder;

    @Column(name = "havebuildassessment")
    private Boolean havebuildassessment;

    @Column(name = "defaulttestbank")
    private Boolean defaulttestbank;

    @Column(name = "language")
    private String language;

    @Column(name = "linkid")
    private String linkid;

    @Column(name = "nameontab")
    private String nameontab;

    @Column(name = "hide")
    private Boolean hide;

    @Column(name = "lessons")
    private Integer lessons;

    @Column(name = "showonlibrary")
    private Boolean showonlibrary;

    @Column(name = "essentialquestions")
    private Boolean essentialquestions;

    @Column(name = "nobreak")
    private Boolean nobreak;

    @Column(name = "jsoninfor")
    private String jsoninfor;

}
