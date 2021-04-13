package com.maranata.apimanagement.feign.client;

import com.github.fge.jsonpatch.JsonPatch;
import com.maranata.apimanagement.dto.MemberDto;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@FeignClient(value = "api-members", url = "http://localhost:8082/v1/members/")
public interface MemberFeignClient {

    public final String AUTH_TOKEN = "Authorization";

    @GetMapping
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<MemberDto>> memberList();

    @GetMapping("/check")
    Boolean checkMember(@RequestParam String personalNumber);

    @PostMapping("/add")
    MemberDto memberPersonAdd(@RequestBody MemberDto memberDto);

    @PostMapping()
    MemberDto memberAdd(@RequestBody MemberDto memberDto);

    @PatchMapping(path = "/{id}")
    ResponseEntity<MemberDto> memberUpdate(@RequestBody JsonPatch memberDto, @PathVariable("id") Long id);

}
