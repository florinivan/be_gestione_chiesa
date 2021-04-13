package com.maranata.apibudgetplanner.feign;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.maranata.commonbean.budgetplanner.entity.Budget;

import feign.Headers;


@FeignClient(value = "api-bugets", url = "http://localhost:8084/v1/budgets/")
public interface BudgetFeignClient {

    @GetMapping
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<Budget>> budgetList();

    @GetMapping("/{id}")
    Optional<Budget> findById(@PathVariable Long id);

    @GetMapping("/{start}/{end}")
    List<Budget>findByRange(@PathVariable Long start,@PathVariable Long end);

    @GetMapping("/{validationId}/{startDate}/{endDate}")
    List<Budget> findAllByRange(@PathVariable Long validationId, @PathVariable Long startDate,@PathVariable Long endDate);

    @GetMapping("/get/{categoryId}")
    List<Budget> findByCategory(@PathVariable Long categoryId);

    @PostMapping("/add")
    Budget addBudget(@RequestBody Budget budget);

    @PutMapping("/{id}" )
    Budget updateBudget(@PathVariable Long id,@RequestBody Budget budget);
}
