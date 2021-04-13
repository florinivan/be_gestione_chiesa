package com.maranata.domainbudgetplanner.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.commonbean.budgetplanner.entity.CategoryType;
import com.maranata.commonbean.budgetplanner.entity.Transaction;
import com.maranata.domainbudgetplanner.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> findAll() {
        return new ResponseEntity<>(transactionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findBy/{type}")
    public ResponseEntity<List<Transaction>> findTransactionsBytypeAndDateBetween(@PathVariable CategoryType type,
                                                                                  @RequestParam Long flussoId,
                                                                                  @RequestParam Long startDate,
                                                                                  @RequestParam Long endDate) {
        List<Transaction> transazioni = transactionService.findTransactionsBytypeAndDateBetween( flussoId, type, startDate, endDate);
        return new ResponseEntity<>(transazioni, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Transaction>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.findbyId(id), HttpStatus.OK);
    }

    @GetMapping("/{startDate}/{endDate}")
    public ResponseEntity<List<Transaction>>findByRange(@PathVariable Long startDate, @PathVariable Long endDate){
        return new ResponseEntity<>(transactionService.findByPeriod(startDate,endDate),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.addTransaction(transaction), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody JsonPatch inTransaction) {
        try {
            Transaction transaction = transactionService.findbyId(id).orElseThrow(RuntimeException::new);
            Transaction transactionPatched = applyPatchToCustomer(inTransaction, transaction);
            transactionService.update(transactionPatched);
            return ResponseEntity.ok(transactionPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping
    public void deleteById(@PathVariable Long id) {
        transactionService.deleteById(id);
    }

    private Transaction applyPatchToCustomer(JsonPatch patch, Transaction targetTransazione) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetTransazione, JsonNode.class));
        return objectMapper.treeToValue(patched, Transaction.class);
    }
}


