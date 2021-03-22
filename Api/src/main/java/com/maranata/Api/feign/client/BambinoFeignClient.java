package com.maranata.Api.feign.client;

import com.github.fge.jsonpatch.JsonPatch;
import com.maranata.Api.dto.BambinoDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@FeignClient(value = "api-bambini", url = "http://localhost:8082/v1/bambini/")
public interface BambinoFeignClient {

    public final String AUTH_TOKEN = "Authorization";

    @GetMapping
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<BambinoDto>> bambinoList();

    @GetMapping("/check")
    Boolean  checkBambino (@RequestParam String codiceFiscale);

    @PostMapping
    ResponseEntity<BambinoDto> addBambino(@RequestBody BambinoDto bambinoDto);

    @PatchMapping(path = "/{id}" )
    ResponseEntity<BambinoDto> update(@PathVariable Long id, @RequestBody JsonPatch bambinoDto);

}
