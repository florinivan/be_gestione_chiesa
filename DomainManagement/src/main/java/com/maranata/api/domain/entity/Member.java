package com.maranata.api.domain.entity;

import lombok.*;
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

    private int baptismDate;

    private String baptismPlace;

    private String baptismOfficer;

    private Date transferredDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Date startDiscipline;

    private Date endDiscipline;

    private String attributes;
    
}
