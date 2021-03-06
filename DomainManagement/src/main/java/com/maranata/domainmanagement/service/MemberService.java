package com.maranata.domainmanagement.service;

import com.maranata.commonbean.management.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maranata.domainmanagement.dao.MemberRepository;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public ResponseEntity<Member> update(Member inMember){
        try {
            Member newMember = memberRepository.findById(inMember.getId()).orElseThrow(RuntimeException::new);
            newMember.setId(inMember.getId());
            newMember.setPhoneNumber(inMember.getPhoneNumber());
            newMember.setStartDiscipline(inMember.getStartDiscipline());
            newMember.setAttributes(inMember.getAttributes());
            newMember.setEmail(inMember.getEmail());
            newMember.setEndDiscipline(inMember.getEndDiscipline());
            newMember.setPnSpouse(inMember.getPnSpouse());
            newMember.setStatus(inMember.getStatus());
            newMember.setTransferredDate(inMember.getTransferredDate());
            memberRepository.save(newMember);
            return ResponseEntity.ok(newMember);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
