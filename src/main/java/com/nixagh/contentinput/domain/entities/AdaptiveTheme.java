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
@Table(name = "adaptivetheme")
public class AdaptiveTheme {
    //head, bodytext, wordlist, programid, themecode

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adaptivethemeid")
    private Long adaptivethemeid;

    @Column(name = "head")
    private String head;

    @Column(name = "bodytext")
    private String bodytext;

    @Column(name = "wordlist")
    private String wordlist;

    @Column(name = "programid")
    private Long programid;

    @Column(name = "themecode")
    private String themecode;

}
