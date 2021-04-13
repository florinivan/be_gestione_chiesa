package com.maranata.apimanagement.feign.client;

import com.github.fge.jsonpatch.JsonPatch;
import com.maranata.apimanagement.dto.ChildDto;

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
    ResponseEntity<Collection<ChildDto>> childList();

    @GetMapping("/check")
    Boolean checkChild(@RequestParam String personalNumber);

    @PostMapping
    ResponseEntity<ChildDto> addChild(@RequestBody ChildDto childDto);

    @PatchMapping(path = "/{id}" )
    ResponseEntity<ChildDto> update(@PathVariable Long id, @RequestBody JsonPatch childDto);

}
