package com.maranata.ApiBudgetPlanner.feign;

import com.maranata.budgetplanner.entity.Budget;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@FeignClient(value = "api-bugets", url = "http://localhost:8084/v1/bugets/")
public interface BudgetFeignClient {

    @GetMapping
    @Headers("Content-Type: application/json")
    ResponseEntity<Collection<Budget>> budgetList();

    @PostMapping("/add")
    Budget addBudget(@RequestBody Budget budget);

    @PatchMapping(path = "/{id}" )
    Budget updateBudget(@RequestBody Budget budgetDto,@PathVariable long id);
}
