package com.maranata.domainmanagement.entity;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "associated")
public class Associate implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="person_id",referencedColumnName = "id")
    private Person person;

    private String email;

    private String phoneNumber;

    private String annotations;

    private Date registrationDate;

    private String flagStatus;

    private String flagCd;

}
