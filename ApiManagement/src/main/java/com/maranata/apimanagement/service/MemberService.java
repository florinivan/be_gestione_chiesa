package com.maranata.apimanagement.service;

import com.github.fge.jsonpatch.JsonPatch;
import com.maranata.apimanagement.feign.client.MemberFeignClient;

import com.maranata.commonbean.management.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MemberService {

    @Autowired
    private MemberFeignClient memberFeignClient;

    public ResponseEntity<Collection<Member>> memberList(){
        return memberFeignClient.memberList();
    }

    public Member memberPersonAdd(Member member) {
        return memberFeignClient.memberPersonAdd(member);
    }

    public Boolean memberCheck(String personalNumber) {
        return memberFeignClient.checkMember(personalNumber);
    }

    public Member memberAdd(Member member) {
        return memberFeignClient.memberAdd(member);
    }

    public ResponseEntity<Member> memberUpdate(JsonPatch member, Long id){
        return memberFeignClient.memberUpdate(member,id);
    }
}
