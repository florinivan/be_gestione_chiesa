package com.maranata.Api.controller;

import com.maranata.Api.dto.MembroDto;
import com.maranata.Api.dto.RegistrazioneMembroDto;
import com.maranata.Api.feign.client.PersonaFeignClient;

import com.maranata.Api.service.RegistrazioneMembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/registrazione")
@RestController
public class RegistrazioneMembroController {

    @Autowired
    RegistrazioneMembroService registrazioneMembroService;

    @PostMapping
    public RegistrazioneMembroDto registrazioneMembro(@RequestBody RegistrazioneMembroDto registrazioneMembroDto){
        return registrazioneMembroService.registrazioneMembro(registrazioneMembroDto);
    }


}
