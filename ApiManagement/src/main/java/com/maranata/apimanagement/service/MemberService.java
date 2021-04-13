package com.maranata.apimanagement.service;

import com.github.fge.jsonpatch.JsonPatch;
import com.maranata.apimanagement.dto.MemberDto;
import com.maranata.apimanagement.feign.client.MemberFeignClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MemberService {

    @Autowired
    private MemberFeignClient memberFeignClient;

    public ResponseEntity<Collection<MemberDto>> memberList(){
        return memberFeignClient.memberList();
    }

    public MemberDto memberPersonAdd(MemberDto memberDto) {
        return memberFeignClient.memberPersonAdd(memberDto);
    }

    public Boolean memberCheck(String personalNumber) {
        return memberFeignClient.checkMember(personalNumber);
    }

    public MemberDto memberAdd(MemberDto memberDto) {
        return memberFeignClient.memberAdd(memberDto);
    }

    public ResponseEntity<MemberDto> memberUpdate(JsonPatch memberDto, Long id){
        return memberFeignClient.memberUpdate(memberDto,id);
    }
}
