package com.maranata.Api.feign.client;

import com.github.fge.jsonpatch.JsonPatch;
import com.maranata.Api.dto.MembroDto;
import com.maranata.Api.dto.PersonaDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@FeignClient(value = "api-membri", url = "http://localhost:8082/v1/membri/")
public interface MembroFeignClient {

    public final String AUTH_TOKEN = "Authorization";

    @GetMapping
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<MembroDto>> membroList();

    @GetMapping("/check")
    Boolean  checkMembro (@RequestParam String codiceFiscale);

    @PostMapping("/add")
    MembroDto membroPersonaAdd(@RequestBody MembroDto membroDto);

    @PostMapping()
    MembroDto membroAdd(@RequestBody MembroDto membroDto);

    @PatchMapping(path = "/{idMembro}")
    ResponseEntity<MembroDto> membroUpdate(@RequestBody JsonPatch membroDto, @PathVariable("idMembro") Long idMembro);

}
