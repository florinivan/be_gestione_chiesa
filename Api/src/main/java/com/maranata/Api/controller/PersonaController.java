package com.maranata.Api.controller;

import com.maranata.Api.dto.MembroDto;
import com.maranata.Api.dto.PersonaDto;
import com.maranata.Api.feign.client.PersonaFeignClient;
import com.maranata.Api.service.RegistrazioneMembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Collection;

@RequestMapping("persona")
@RestController
public class PersonaController {

    @Autowired
    private PersonaFeignClient personaFeignClient;
    @Autowired
    RegistrazioneMembroService registrazioneMembroService;

    @GetMapping("/list")
    public Collection<PersonaDto> personaList(Model model) {
        model.addAttribute("persone", personaFeignClient.personaList().getBody());
        return personaFeignClient.personaList().getBody();
    }

    @GetMapping("/edit")
    public Collection<PersonaDto> personaOne(@RequestParam String codiceFiscale,Model model) {
        model.addAttribute("codiceFiscale", registrazioneMembroService.findBycodiceFiscale(codiceFiscale).getBody());
        return registrazioneMembroService.findBycodiceFiscale(codiceFiscale).getBody();
    }

    @PutMapping("/add")
    public PersonaDto personaAdd (@RequestBody PersonaDto personaDto){
        return registrazioneMembroService.personaAdd(personaDto);

    }
}
