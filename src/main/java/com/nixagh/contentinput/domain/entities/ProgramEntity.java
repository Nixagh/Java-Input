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
@Table(name = "program")
public class ProgramEntity {
    //"name", programseriesid, createddate, modifieddate, seisbn, bookshelficon, bookshelficon2x, headergraphic, headergraphic2x, footerimage, footerimage2x, uselevel, fullaccessenabled, uribackgroundstudent2x, uribackgroundstudent, jsoninfor

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "programid")
    private Long programid;

    @Column(name = "name")
    private String name;

    @Column(name = "programseriesid")
    private Long programseriesid;

    @Column(name = "createddate")
    private Timestamp createddate = new Timestamp(System.currentTimeMillis());

    @Column(name = "modifieddate")
    private Timestamp modifieddate;

    @Column(name = "seisbn")
    private String seisbn;

    @Column(name = "bookshelficon")
    private String bookshelficon;

    @Column(name = "bookshelficon2x")
    private String bookshelficon2x;

    @Column(name = "headergraphic")
    private String headergraphic;

    @Column(name = "headergraphic2x")
    private String headergraphic2x;

    @Column(name = "footerimage")
    private String footerimage;

    @Column(name = "footerimage2x")
    private String footerimage2x;

    @Column(name = "uselevel")
    private Boolean uselevel;

    @Column(name = "fullaccessenabled")
    private Boolean fullaccessenabled;

    @Column(name = "uribackgroundstudent2x")
    private String uribackgroundstudent2x;

    @Column(name = "uribackgroundstudent")
    private String uribackgroundstudent;

    @Column(name = "jsoninfor")
    private String jsoninfor;
}
