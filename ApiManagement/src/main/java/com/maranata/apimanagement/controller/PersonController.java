package com.maranata.apimanagement.controller;

import com.maranata.commonbean.management.entity.Person;
import com.maranata.commonbean.management.entity.Spouse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.maranata.apimanagement.service.PersonService;

import java.util.Collection;

@Api(value = "Person Resource")
@RequestMapping("person")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/list")
    public Collection<Person> personaList(Model model) {
        model.addAttribute("persons", personService.personList().getBody());
        return personService.personList().getBody();
    }

    @GetMapping("/edit")
    public Collection<Person> personOne(@RequestParam String personalNumber, Model model) {
        model.addAttribute("personalNumber", personService.findBypersonalNumber(personalNumber).getBody());
        return personService.findBypersonalNumber(personalNumber).getBody();
    }

    @PostMapping("/add")
    public ResponseEntity<Person> personAdd (@RequestBody Person person){
        return personService.personAdd(person);
    }

    @PostMapping("/addSpouse")
    public ResponseEntity<Person> spouseAdd(@RequestBody Spouse spouse){
        return  personService.personAdd(spouse.getPerson());
    }

    @PutMapping("/update")
    public ResponseEntity<Person> personUpdate(@RequestBody Person person, @PathVariable Long id){
        return personService.personUpdate(person,id);
    }
}
