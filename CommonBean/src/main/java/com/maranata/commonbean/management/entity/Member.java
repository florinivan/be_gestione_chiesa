package com.maranata.commonbean.management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "members")
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="person_id",referencedColumnName = "id")
    private Person person;

    private String email;

    private String phoneNumber;

    private String pnSpouse;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date baptismDate;

    private String baptismPlace;

    private String baptismOfficer;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date transferredDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDiscipline;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDiscipline;

    private String attributes;
    
}
