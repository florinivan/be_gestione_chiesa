package com.maranata.Api.feign.client;

import com.maranata.Api.dto.MembroDto;
import com.maranata.Api.dto.PersonaDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@FeignClient(value = "api-membri", url = "http://localhost:8082/rest/membri/")
public interface MembroFeignClient {

    public final String AUTH_TOKEN = "Authorization";

    @GetMapping
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<MembroDto>> membroList();

    @GetMapping("/check")
    Boolean  checkMembro (@RequestParam String codiceFiscale);

    @PostMapping
    MembroDto membroAdd(@RequestBody MembroDto membroDto);

}
