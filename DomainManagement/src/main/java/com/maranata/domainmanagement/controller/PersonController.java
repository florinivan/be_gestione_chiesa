package com.maranata.domainmanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.commonbean.management.entity.Person;
import com.maranata.domainmanagement.dao.PersonRepository;
import com.maranata.domainmanagement.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private ObjectMapper  objectMapper = new ObjectMapper();

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Person>> findByid(@PathVariable long id){
        return new ResponseEntity<Optional<Person>>(personService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Person>> findPersonByPn(@RequestParam String personalNumber) {
        return new ResponseEntity<>(personService.findPersonByPn(personalNumber), HttpStatus.OK);
    }

    @GetMapping("/list/{personalNumber}")
    public ResponseEntity<List<Person>> findAllMembersChildrenByRepository() {
        return new ResponseEntity<>(personService.findAllMembersChildrenByRepository(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        return new ResponseEntity<>(personService.addPerson(person), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}" )
    public ResponseEntity<Person>updatePerson(@PathVariable Long id, @RequestBody JsonPatch inPerson){
        try {
            Person person = personService.findById(id).orElseThrow(RuntimeException::new);
            Person personPatched = applyPatchToCustomer(inPerson, person);
            personService.update(personPatched);
            return ResponseEntity.ok(personPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable Long id) {
       personRepository.deleteById(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Person applyPatchToCustomer(
            JsonPatch patch, Person targetPerson) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetPerson, JsonNode.class));
        return objectMapper.treeToValue(patched, Person.class);
    }
}
