package com.maranata.Api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MemberDto {

    private Long id;
    private PersonDto person;
    private String pnSpouse;
    private String email;
    private String phoneNumber;
    private Date baptismDate;
    private String baptismPlace;
    private String baptismOfficer;
    private Date transferredDate;
    private boolean widower;
    private boolean divorced;
    private Date startDiscipline;
    private Date endDiscipline;
    private String attributes;





}
