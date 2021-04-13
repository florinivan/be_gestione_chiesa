package com.maranata.domainmanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.domainmanagement.dao.MemberRepository;
import com.maranata.domainmanagement.entity.Member;
import com.maranata.domainmanagement.service.MemberService;
import com.maranata.domainmanagement.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PersonService personService;
    @Autowired
    MemberService memberService;

    @GetMapping
    public ResponseEntity<List<Member>> findAll(){
        List<Member> member = memberRepository.findAll();
        return new ResponseEntity<>(member,HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkMemberByPn(@RequestParam String personalNumber) {
        return new ResponseEntity<Boolean>( memberRepository.existMemberBypersonalNumber(personalNumber),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member){
        return new ResponseEntity<>(memberRepository.save(member),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Member> addPersonMember(@RequestBody Member member) {
        return new ResponseEntity<>(personService.addPersonMember(member),HttpStatus.OK);
    }


    @PatchMapping(path = "/{id}" )
    public ResponseEntity<Member>updateMember(@PathVariable Long id, @RequestBody JsonPatch inMember){
        try {
            Member member = memberRepository.findById(id).orElseThrow(RuntimeException::new);
            Member memberPatched = applyPatchToCustomer(inMember, member);
            memberService.update(memberPatched);
            return ResponseEntity.ok(memberPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus>deleteMember(@PathVariable Long id){
        memberRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Member applyPatchToCustomer(JsonPatch patch, Member targetMember) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetMember, JsonNode.class));
        return objectMapper.treeToValue(patched, Member.class);
    }


}