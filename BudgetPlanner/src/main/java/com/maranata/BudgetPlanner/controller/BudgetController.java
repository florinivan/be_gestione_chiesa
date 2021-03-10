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
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    BudgetService budgetService;
    @Autowired
    BudgetRepository budgetRepository;

    @GetMapping
    public ResponseEntity<List<Budget>> findAll(){
        return new ResponseEntity<>(budgetService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{flussoId}/{periodo}")
    public ResponseEntity<List<Budget>>findAllByFlussoAndPeriodo(@PathVariable Long flussoId, @PathVariable String periodo){
        return new ResponseEntity<>(budgetService.findAllByFlussoAndPeriodo(flussoId,periodo),HttpStatus.OK);
    }

    @GetMapping("/tipo")
    public ResponseEntity<List<Budget>> findAllByCategoria(@RequestBody TipoCategoria categoria){
        List<Budget> budget = budgetRepository.findAllByCategoria(categoria);
        return new ResponseEntity<>(budget,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Budget> add(@RequestBody Budget budget){
        return new ResponseEntity<>(budgetService.addBudgetByCategory(budget),HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}" )
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody JsonPatch inBudget) {
        try {
            Budget budget = budgetService.findById(id).orElseThrow(RuntimeException::new);
            Budget budgetPatched = applyPatchToBudget(inBudget, budget);
            budgetService.update(budgetPatched);
            return ResponseEntity.ok(budgetPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
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
