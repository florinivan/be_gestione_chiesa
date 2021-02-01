package com.maranata.Api.feign;

import com.maranata.Api.dto.PersonaDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@FeignClient(value = "api-registrazione", url = "http://localhost:8082/rest/persone/")
public interface RegistrazioneFeignClient {

    public final String AUTH_TOKEN = "Authorization";

    @GetMapping
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<PersonaDto>> personaList();

}
