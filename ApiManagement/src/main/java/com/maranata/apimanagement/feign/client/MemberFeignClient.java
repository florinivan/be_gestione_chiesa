package com.maranata.apimanagement.feign.client;

import com.github.fge.jsonpatch.JsonPatch;

import com.maranata.commonbean.management.entity.Member;
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
    ResponseEntity<Collection<Member>> memberList();

    @GetMapping("/check")
    Boolean checkMember(@RequestParam String personalNumber);

    @PostMapping("/add")
    Member memberPersonAdd(@RequestBody Member member);

    @PostMapping()
    Member memberAdd(@RequestBody Member member);

    @PatchMapping(path = "/{id}")
    ResponseEntity<Member> memberUpdate(@RequestBody JsonPatch member, @PathVariable("id") Long id);

}
