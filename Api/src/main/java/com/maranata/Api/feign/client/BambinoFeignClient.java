package com.maranata.Api.feign.client;

import com.maranata.Api.dto.BambinoDto;
import com.maranata.Api.dto.PersonaDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@FeignClient(value = "api-bambini", url = "http://localhost:8082/rest/bambini/")
public interface BambinoFeignClient {

    public final String AUTH_TOKEN = "Authorization";

    @GetMapping
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<BambinoDto>> bambinoList();

    @GetMapping("/check")
    Boolean  checkBambino (@RequestParam String codiceFiscale);

    @PostMapping
    BambinoDto addBambino(BambinoDto bambinoDto);

}
