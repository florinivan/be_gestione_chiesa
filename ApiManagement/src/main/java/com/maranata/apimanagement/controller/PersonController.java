package com.maranata.apimanagement.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.maranata.apimanagement.dto.PersonDto;
import com.maranata.apimanagement.dto.SpouseDto;
import com.maranata.apimanagement.service.PersonService;

import java.util.Collection;

@Api(value = "Persona Resource")
@RequestMapping("persona")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/list")
    public Collection<PersonDto> personaList(Model model) {
        model.addAttribute("persone", personService.personList().getBody());
        return personService.personList().getBody();
    }

    @GetMapping("/edit")
    public Collection<PersonDto> personaOne(@RequestParam String codiceFiscale, Model model) {
        model.addAttribute("codiceFiscale", personService.findBypersonalNumber(codiceFiscale).getBody());
        return personService.findBypersonalNumber(codiceFiscale).getBody();
    }

    @PostMapping("/add")
    public ResponseEntity<PersonDto> personaAdd (@RequestBody PersonDto personDto){
        return personService.personAdd(personDto);
    }

    @PostMapping("/addConiuge")
    public ResponseEntity<PersonDto> coniugeAdd(@RequestBody SpouseDto spouseDto){
        return  personService.personAdd(spouseDto.getPerson());
    }

    @PutMapping("/update")
    public ResponseEntity<PersonDto> personaUpdate(@RequestBody PersonDto personDto, @PathVariable Long id){
        return personService.personUpdate(personDto,id);
    }
}
