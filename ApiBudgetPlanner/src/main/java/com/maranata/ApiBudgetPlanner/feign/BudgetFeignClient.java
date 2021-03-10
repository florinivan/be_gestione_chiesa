package com.maranata.ApiBudgetPlanner.feign;

import com.maranata.budgetplanner.entity.Budget;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@FeignClient(value = "api-bugets", url = "http://localhost:8084/v1/budgets/")
public interface BudgetFeignClient {

    @GetMapping
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<Budget>> budgetList();

    @GetMapping("/{flussoId}/{periodo}")
    List<Budget> findAllByFlussoAndPeriodo(@PathVariable Long flussoId, @PathVariable String periodo);

    @PostMapping("/add")
    Budget addBudget(@RequestBody Budget budget);

    @PatchMapping(path = "/{id}" )
    Budget updateBudget(@RequestBody Budget budgetDto,@PathVariable long id);
}
