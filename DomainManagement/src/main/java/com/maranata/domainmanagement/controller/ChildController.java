package com.maranata.domainmanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.commonbean.management.entity.Child;
import com.maranata.domainmanagement.dao.ChildRepository;
import com.maranata.domainmanagement.dao.PersonRepository;
import com.maranata.domainmanagement.service.ChildService;
import com.maranata.domainmanagement.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/children")
public class ChildController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    ChildRepository childRepository;
    @Autowired
    PersonService personService;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    ChildService bambinoService;

    @GetMapping
    public ResponseEntity<List<Child>> findAll(){
        List<Child> child = childRepository.findAll();
        return new ResponseEntity<>(child,HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkChildByPersonalNumber(@RequestParam String personalNumber) {
        return new ResponseEntity<Boolean>( childRepository.existChildByPersonalNumber(personalNumber),HttpStatus.OK);
    }

    @GetMapping("/{father_personal_number}")
    public Child getChildByFather(@PathVariable String fatherPersonalNumber){
       return childRepository.findChildByFather(fatherPersonalNumber);
    }

    @GetMapping("/{mather_personal_number}")
    public Child getChildByMather(@PathVariable String matherPersonalNumber) {
        return childRepository.findChildByMather(matherPersonalNumber);
    }

    @PostMapping("/add")
    public ResponseEntity<Child> addChild(@RequestBody Child child) {
        return new ResponseEntity<>(personService.addPersonChild(child),HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}" )
    public ResponseEntity<Child> updateChild(@PathVariable Long id, @RequestBody JsonPatch inChild) {
        try {
            Child child = childRepository.findById(id).orElseThrow(RuntimeException::new);
            Child childPatched = applyPatchToCustomer(inChild, child);
            bambinoService.update(childPatched);
            return ResponseEntity.ok(childPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus>deleteChild(@PathVariable Long id,@PathVariable Child child){
        personRepository.deleteById(child.getPerson().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Child applyPatchToCustomer(JsonPatch patch, Child targetChild) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetChild, JsonNode.class));
        return objectMapper.treeToValue(patched, Child.class);
    }

}
