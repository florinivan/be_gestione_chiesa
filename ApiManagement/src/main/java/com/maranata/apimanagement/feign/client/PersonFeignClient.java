package com.maranata.apimanagement.feign.client;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.maranata.apimanagement.dto.PersonDto;
import com.maranata.apimanagement.dto.SpouseDto;

import java.util.Collection;

@FeignClient(value = "api-persons", url = "http://localhost:8082/v1/persons/")
public interface PersonFeignClient {

    public final String AUTH_TOKEN = "Authorization";

    @GetMapping
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<PersonDto>> personList();

    @GetMapping("/find")
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<PersonDto>> findBypersonalNumber(@RequestParam("personalNumber") String personalNumber);

    @PostMapping
    ResponseEntity<PersonDto> personAdd(@RequestBody PersonDto personDto);

    @PostMapping("/addSpouse")
    ResponseEntity<SpouseDto> spouseAdd(@RequestBody SpouseDto spouseDto);

    @PatchMapping(path = "/{id}" )
    ResponseEntity<PersonDto> personUpdate(@RequestBody PersonDto personDto, @PathVariable Long id);

}
