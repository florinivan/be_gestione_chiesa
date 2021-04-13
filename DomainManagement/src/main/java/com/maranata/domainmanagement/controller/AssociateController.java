package com.maranata.domainmanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.domainmanagement.dao.AssociateRepository;
import com.maranata.domainmanagement.entity.Associate;
import com.maranata.domainmanagement.service.AssociateService;
import com.maranata.domainmanagement.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/associates")
public class AssociateController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    AssociateRepository associateRepository;
    @Autowired
    PersonService personService;
    @Autowired
    AssociateService associateService;

    @GetMapping
    public ResponseEntity<List<Associate>> findAll(){
        List<Associate> associate = associateRepository.findAll();
        return new ResponseEntity<>(associate,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Associate> findById(@PathVariable Long id){
        Associate associate = associateRepository.findById(id).get();
        return new ResponseEntity<>(associate,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Associate> addAssociate(@RequestBody Associate associate) {
        return new ResponseEntity<>(personService.addPersonAssociate(associate), HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}" )
    public ResponseEntity<Associate>updateAssociate(@PathVariable Long id, @RequestBody JsonPatch inAssociate){
        try {
            Associate associate = associateRepository.findById(id).orElseThrow(RuntimeException::new);
            Associate associatePatched = applyPatchToCustomer(inAssociate, associate);
            associateService.update(associatePatched);
            return ResponseEntity.ok(associatePatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private Associate applyPatchToCustomer(JsonPatch patch, Associate targetAssociate) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetAssociate, JsonNode.class));
        return objectMapper.treeToValue(patched, Associate.class);
    }

}
