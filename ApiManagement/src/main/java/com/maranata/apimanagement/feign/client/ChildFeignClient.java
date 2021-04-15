package com.maranata.apimanagement.feign.client;

import com.github.fge.jsonpatch.JsonPatch;

import com.maranata.commonbean.management.entity.Child;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@FeignClient(value = "api-children", url = "http://localhost:8082/v1/children/")
public interface ChildFeignClient {

    public final String AUTH_TOKEN = "Authorization";

    @GetMapping
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<Child>> childList();

    @GetMapping("/check")
    Boolean checkChild(@RequestParam String personalNumber);

    @PostMapping
    ResponseEntity<Child> addChild(@RequestBody Child child);

    @PatchMapping(path = "/{id}" )
    ResponseEntity<Child> update(@PathVariable Long id, @RequestBody JsonPatch child);

}
