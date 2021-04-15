package com.maranata.apimanagement.feign.client;

import com.maranata.commonbean.management.entity.Person;
import com.maranata.commonbean.management.entity.Spouse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;

@FeignClient(value = "api-persons", url = "http://localhost:8082/v1/persons/")
public interface PersonFeignClient {

    public final String AUTH_TOKEN = "Authorization";

    @GetMapping
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<Person>> personList();

    @GetMapping("/find")
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<Person>> findBypersonalNumber(@RequestParam("personalNumber") String personalNumber);

    @PostMapping
    ResponseEntity<Person> personAdd(@RequestBody Person person);

    @PostMapping("/addSpouse")
    ResponseEntity<Spouse> spouseAdd(@RequestBody Spouse spouse
    );

    @PatchMapping(path = "/{id}" )
    ResponseEntity<Person> personUpdate(@RequestBody Person person, @PathVariable Long id);

}
