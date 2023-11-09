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
@Table
public class ProgramSeriesEntity {
    //"name", description, createddate, modifieddate, imageicon, imageicon2x, cssclass, needconverttoise, displayorder

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "programseriesid")
    private Long programseriesid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "createddate")
    private Timestamp createddate = new Timestamp(System.currentTimeMillis());

    @Column(name = "modifieddate")
    private Timestamp modifieddate;

    @Column(name = "imageicon")
    private String imageicon;

    @Column(name = "imageicon2x")
    private String imageicon2x;

    @Column(name = "cssclass")
    private String cssclass;

    @Column(name = "needconverttoise")
    private Boolean needconverttoise;

    @Column(name = "displayorder")
    private Integer displayorder;

}
