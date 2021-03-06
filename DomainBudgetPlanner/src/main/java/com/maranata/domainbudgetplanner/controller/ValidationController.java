package com.maranata.domainbudgetplanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.commonbean.budgetplanner.entity.Validation;
import com.maranata.domainbudgetplanner.service.ValidationService;

@RestController
@RequestMapping("/validations")
public class ValidationController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    ValidationService validationService;

    @GetMapping
    public ResponseEntity<List<Validation>> findAll(){
        return new ResponseEntity<>(validationService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Validation>add(@RequestBody Validation validation){
        return new ResponseEntity<>(validationService.add(validation),HttpStatus.CREATED);
    }

   @PatchMapping
    public ResponseEntity<Validation> update(@PathVariable Long id, @RequestBody JsonPatch inValidation) {
        try {
            Validation validation = validationService.findById(id).orElseThrow(RuntimeException::new);
            Validation validationPatched = applyPatchToValidation(inValidation, validation);
            validationService.update(validationPatched);
            return ResponseEntity.ok(validationPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    private Validation applyPatchToValidation(JsonPatch patch, Validation targetFlusso) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetFlusso, JsonNode.class));
        return objectMapper.treeToValue(patched, Validation.class);
    }

}
