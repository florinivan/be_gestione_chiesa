package com.maranata.apibudgetplanner.controller;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maranata.apibudgetplanner.service.CategoryService;
import com.maranata.apibudgetplanner.service.TransactionService;
import com.maranata.commonbean.budgetplanner.dto.TransactionResponseDto;
import com.maranata.commonbean.budgetplanner.entity.Point;
import com.maranata.commonbean.budgetplanner.entity.Transaction;
import com.maranata.commonbean.budgetplanner.entity.UsageSummary;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<Collection<Transaction>> transazioneList(){
        return new ResponseEntity<>(transactionService.transazioniList().getBody(),HttpStatus.OK);
    }

    @GetMapping("/summary")
    public ResponseEntity<List<Point>> findSummary() {
        return new ResponseEntity<>(transactionService.findTransactionUsage(),HttpStatus.OK);
    }

    @GetMapping("/monthly")
    public ResponseEntity<List<Point>> findMonthly(){
        return new ResponseEntity<>(categoryService.findMonthlyTransactionUsage(),HttpStatus.OK);
    }

    @GetMapping("/{month}/{year}")
    public ResponseEntity<UsageSummary>findUsageSummary(@PathVariable int month, @PathVariable int year){
        return new ResponseEntity<>(transactionService.findUsageSummary(month,year),HttpStatus.OK);
    }

    @PostMapping("/{budgetId}")
    ResponseEntity<TransactionResponseDto> addTransaction(@RequestBody Transaction transaction, @PathVariable Long budgetId){
        return new ResponseEntity<>(transactionService.addTransaction(transaction,budgetId), HttpStatus.CREATED);
    }
}
