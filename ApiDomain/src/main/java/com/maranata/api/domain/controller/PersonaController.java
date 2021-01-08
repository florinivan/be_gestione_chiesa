package com.maranata.api.domain.controller;

import com.maranata.api.domain.entity.Persona;
import com.maranata.api.domain.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/domain/persone")
public class PersonaController {

    //TODO IMPLEMENT INSERT METHOD, FIND ALL

    @Autowired
    PersonaService personaService;

    @GetMapping("/persone")
    public List<Persona> getAllPersona(){
        return personaService.getAllPersona();
    }

    @GetMapping("/{persone/{nome}")
    public List<Persona> getAllPersonaByName(@PathVariable String name) {
        return personaService.getAllPersoneByName(name);
    }

    @GetMapping("/persone/{codiceFiscale}")
    public List <Persona> getAllMembriBambiniByRepository(){
        return personaService.getAllMembriBambiniByRepository();
    }

    @PostMapping("/persone")
    public Persona insertPersona(@RequestBody Persona persona){
       return personaService.insertPersona(persona);
    }



}
