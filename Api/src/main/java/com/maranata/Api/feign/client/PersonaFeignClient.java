package com.maranata.Api.feign.client;

import com.maranata.Api.dto.MembroDto;
import com.maranata.Api.dto.PersonaDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@FeignClient(value = "api-persone", url = "http://localhost:8082/rest/persone/", decode404= true)
public interface PersonaFeignClient {

    public final String AUTH_TOKEN = "Authorization";

    @GetMapping
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<PersonaDto>> personaList();

    @GetMapping("/find")
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<PersonaDto>> findBycodiceFiscale(@RequestParam("codiceFiscale") String codiceFiscale);

    @PostMapping
    PersonaDto addPersona(@RequestBody PersonaDto personaDto);


}
