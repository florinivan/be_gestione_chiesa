package com.maranata.api.domain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.api.domain.dao.PersonaRepository;
import com.maranata.api.domain.entity.Persona;
import com.maranata.api.domain.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persone")
public class PersonaController {

    private ObjectMapper  objectMapper = new ObjectMapper();

    @Autowired
    PersonaService personaService;

    @Autowired
    PersonaRepository personaRepository;

    @GetMapping
    public ResponseEntity<List<Persona>> findAll() {
        return new ResponseEntity<>(personaService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Persona>> findByid(@PathVariable long id){
        return new ResponseEntity<Optional<Persona>>(personaService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Persona>> findPersonaByCf(@RequestParam String codiceFiscale) {
        return new ResponseEntity<>(personaService.findPersonaByCf(codiceFiscale), HttpStatus.OK);
    }

    @GetMapping("/list/{codiceFiscale}")
    public ResponseEntity<List<Persona>> findAllMembriBambiniByRepository() {
        return new ResponseEntity<>(personaService.findAllMembriBambiniByRepository(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Persona> addPersona(@RequestBody Persona persona) {
        return new ResponseEntity<>(personaService.addPersona(persona), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}" )
    public ResponseEntity<Persona>updatePersona(@PathVariable Long id, @RequestBody JsonPatch inPersona){
        try {
            Persona persona = personaService.findById(id).orElseThrow(RuntimeException::new);
            Persona personaPatched = applyPatchToCustomer(inPersona, persona);
            personaService.updatePersona(personaPatched);
            return ResponseEntity.ok(personaPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deletePersone(@PathVariable Long idPersona) {
       personaRepository.deleteById(idPersona);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Persona applyPatchToCustomer(
            JsonPatch patch, Persona targetPersona) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetPersona, JsonNode.class));
        return objectMapper.treeToValue(patched, Persona.class);
    }
}
