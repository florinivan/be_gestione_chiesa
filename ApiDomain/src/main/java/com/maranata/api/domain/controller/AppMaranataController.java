package com.maranata.api.domain.controller;

import com.maranata.api.domain.entity.Persona;
import com.maranata.api.domain.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppMaranataController {

    @Autowired
    PersonaService personaService;

    @GetMapping("/{name}")
    public ResponseEntity <List<Persona>> fetchAllPersonaByName(@PathVariable(name="name")String name) {
        return ResponseEntity.ok(personaService.getAllPersoneByName(name));
    }

}
