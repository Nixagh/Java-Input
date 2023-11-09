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
@Table(name = "themeprogramtoc")
public class ThemeProgramTOCEntity {
//adaptivethemeid, programtocid, displayorder

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "themeprogramtocid")
    private Long themeprogramtocid;

    @Column(name = "adaptivethemeid")
    private Long adaptivethemeid;

    @Column(name = "programtocid")
    private Long programtocid;

    @Column(name = "displayorder")
    private Integer displayorder;
}
