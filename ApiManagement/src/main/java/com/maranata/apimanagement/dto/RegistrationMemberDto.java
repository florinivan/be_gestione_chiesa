package com.maranata.apimanagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegistrationMemberDto {

    private MemberDto memberDto;
    private SpouseDto spouseDto;
    private List<ChildDto> children;


}
