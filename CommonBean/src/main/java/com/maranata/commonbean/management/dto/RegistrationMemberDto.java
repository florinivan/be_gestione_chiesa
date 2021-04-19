package com.maranata.commonbean.management.dto;

import com.maranata.commonbean.management.entity.Child;
import com.maranata.commonbean.management.entity.Member;
import com.maranata.commonbean.management.entity.Spouse;
import lombok.Data;

import java.util.List;

@Data
public class RegistrationMemberDto {

    private Member member;
    private Spouse spouse;
    private List<Child> children;
}

