package com.maranata.budgetplanner.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.budgetplanner.dao.BudgetRepository;
import com.maranata.budgetplanner.entity.*;
import com.maranata.budgetplanner.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    BudgetService budgetService;

    @GetMapping
    public ResponseEntity<List<Budget>> findAll(){
        return new ResponseEntity<>(budgetService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Budget>> findById(@PathVariable Long id){
        return new ResponseEntity<>(budgetService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/{startDate}/{endDate}")
    public ResponseEntity<List<Budget>> findByDate(@PathVariable Long startDate,@PathVariable Long endDate){
        return new ResponseEntity<>(budgetService.findByDate(startDate, endDate),HttpStatus.OK);
    }

    @GetMapping("/{validationId}/{startDate}/{endDate}")
    public ResponseEntity<List<Budget>>findAllByRange(@PathVariable Long validationId, @PathVariable Long startDate,@PathVariable Long endDate){
        return new ResponseEntity<>(budgetService.findAllByValidationAndPeriod(validationId,startDate,endDate),HttpStatus.OK);
    }

    @GetMapping("/get/{categoryId}")
    public ResponseEntity<List<Budget>> findByCategory(@PathVariable Long categoryId){
        List<Budget> budget = budgetService.findByCategory(categoryId);
        return new ResponseEntity<>(budget,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Budget> addBudget(@RequestBody Budget budget){
        return new ResponseEntity<>(budgetService.addBudget(budget),HttpStatus.CREATED);
    }

    @PutMapping("/{id}" )
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id,@RequestBody Budget budget){
        return  new ResponseEntity<>(budgetService.update(id,budget),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        budgetService.deleteById(id);
    }

    private Budget applyPatchToBudget(JsonPatch patch, Budget targetBudget) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetBudget, JsonNode.class));
        return objectMapper.treeToValue(patched, Budget.class);
    }

}
