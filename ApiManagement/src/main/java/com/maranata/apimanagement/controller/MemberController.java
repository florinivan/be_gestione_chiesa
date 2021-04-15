package com.maranata.apimanagement.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.maranata.apimanagement.service.MemberService;

import com.maranata.commonbean.management.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("member")
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/list")
    public Collection<Member> memberList(Model model) {
        model.addAttribute("members", memberService.memberList().getBody());
        return memberService.memberList().getBody();
    }

    @GetMapping("/edit")
    public boolean memberOne(@RequestParam String personalNumber, Model model) {
        model.addAttribute("personalNumber", memberService.memberCheck(personalNumber));
        return memberService.memberCheck(personalNumber);
    }

    @PostMapping("/addMember")
    public ResponseEntity<Member> memberAdd(@RequestBody Member member){
        return new ResponseEntity<>(memberService.memberAdd(member), HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<Member> memberPersonAdd(@RequestBody Member member){
        return new ResponseEntity<>(memberService.memberPersonAdd(member),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Member> memberUpdate(@RequestBody JsonPatch member, @PathVariable Long id){
        return  memberService.memberUpdate(member,id);
    }
}
