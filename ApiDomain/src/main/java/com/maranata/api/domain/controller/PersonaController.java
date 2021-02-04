package com.maranata.api.domain.controller;

import com.maranata.api.domain.dao.PersonaRepository;
import com.maranata.api.domain.entity.Persona;
import com.maranata.api.domain.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persone")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @Autowired
    PersonaRepository personaRepository;

    @GetMapping
    public ResponseEntity<List<Persona>> findAll() {
        return new ResponseEntity<>(personaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Persona>> findAllById(@PathVariable Long id) {
        return new ResponseEntity<>(personaService.findAllById(id), HttpStatus.OK);
    }

    @GetMapping("/{codiceFiscale}")
    public ResponseEntity<Persona> findPersonaByCf(@PathVariable String codiceFiscale) {
        return new ResponseEntity<>(personaService.findPersonaByCodiceFiscale(codiceFiscale), HttpStatus.OK);
    }

    @GetMapping("/list/{codiceFiscale}")
    public ResponseEntity<List<Persona>> findAllMembriBambiniByRepository() {
        return new ResponseEntity<>(personaService.findAllMembriBambiniByRepository(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Persona> addPersona(@RequestBody Persona persona) {
        return new ResponseEntity<>(personaService.addPersona(persona), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona>updatePersona(@RequestBody Persona persona, @PathVariable Long id){
        return new ResponseEntity<>(personaService.updatePersona(id,persona),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllPersone() {
        try {
            personaService.deleteAllPersone();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{codiceFiscale}")
    public ResponseEntity<Persona>deleteByCodiceFiscale( @PathVariable String codiceFiscale) {
        return new ResponseEntity<>(personaService.deleteByCodiceFiscale(codiceFiscale), HttpStatus.OK);
    }
}
