package com.maranata.Api.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.maranata.Api.dto.MemberDto;
import com.maranata.Api.service.MemberService;
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
    public Collection<MemberDto> memberList(Model model) {
        model.addAttribute("members", memberService.memberList().getBody());
        return memberService.memberList().getBody();
    }

    @GetMapping("/edit")
    public boolean memberOne(@RequestParam String personalNumber, Model model) {
        model.addAttribute("personalNumber", memberService.memberCheck(personalNumber));
        return memberService.memberCheck(personalNumber);
    }

    @PostMapping("/addMember")
    public ResponseEntity<MemberDto> memberAdd(@RequestBody MemberDto memberDto){
        return new ResponseEntity<>(memberService.memberAdd(memberDto), HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<MemberDto> memberPersonAdd(@RequestBody MemberDto memberDto){
        return new ResponseEntity<>(memberService.memberPersonAdd(memberDto),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MemberDto> memberUpdate(@RequestBody JsonPatch memberDto, @PathVariable Long id){
        return  memberService.memberUpdate(memberDto,id);
    }
}
