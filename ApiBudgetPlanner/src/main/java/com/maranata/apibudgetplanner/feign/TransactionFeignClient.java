package com.maranata.apibudgetplanner.feign;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.maranata.commonbean.budgetplanner.entity.CategoryType;
import com.maranata.commonbean.budgetplanner.entity.Transaction;

import feign.Headers;


@FeignClient(value = "api-bugets", url = "http://localhost:8084/v1/transactions/")
public interface TransactionFeignClient {

    @GetMapping
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<Transaction>> transactionList();

    @GetMapping("/findBy/{type}")
    @Headers("Content-Type: application/json")
    List<Transaction> getTransactionsByTypeAndDateBetween(@PathVariable CategoryType type,
                                                          @RequestParam Long validationId,
                                                          @RequestParam Long startDate,
                                                          @RequestParam Long endDate);


    @GetMapping("/{id}")
    ResponseEntity<Optional<Transaction>> checkById(@PathVariable long id);

    @GetMapping("/{start}/{end}")
    List<Transaction> findByRange(@PathVariable Long start, @PathVariable Long end);

    @PostMapping
    Transaction addTransaction(@RequestBody Transaction transaction);

    @PatchMapping(path = "/{id}" )
    Transaction updateTransaction(@RequestBody Transaction transaction, @PathVariable Long id);
}
