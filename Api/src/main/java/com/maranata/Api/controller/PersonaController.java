package com.maranata.Api.controller;

import com.maranata.Api.dto.ConiugeDto;
import com.maranata.Api.dto.PersonaDto;
import com.maranata.Api.service.PersonaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Api(value = "Persona Resource")
@RequestMapping("persona")
@RestController
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/list")
    public Collection<PersonaDto> personaList(Model model) {
        model.addAttribute("persone", personaService.personaList().getBody());
        return personaService.personaList().getBody();
    }

    @GetMapping("/edit")
    public Collection<PersonaDto> personaOne(@RequestParam String codiceFiscale,Model model) {
        model.addAttribute("codiceFiscale", personaService.findBycodiceFiscale(codiceFiscale).getBody());
        return personaService.findBycodiceFiscale(codiceFiscale).getBody();
    }

    @PostMapping("/add")
    public ResponseEntity<PersonaDto> personaAdd (@RequestBody PersonaDto personaDto){
        return personaService.personaAdd(personaDto);
    }

    @PostMapping("/addConiuge")
    public ResponseEntity<PersonaDto> coniugeAdd(@RequestBody ConiugeDto coniugeDto){
        return  personaService.personaAdd(coniugeDto.getPersona());
    }

    @PutMapping("/update")
    public ResponseEntity<PersonaDto> personaUpdate(@RequestBody PersonaDto personaDto, @PathVariable Long id){
        return personaService.personaUpdate(personaDto,id);
    }
}
