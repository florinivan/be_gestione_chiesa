package com.maranata.ApiBudgetPlanner.controller;

import com.maranata.ApiBudgetPlanner.feign.BudgetFeignClient;
import com.maranata.ApiBudgetPlanner.service.BudgetService;
import com.maranata.budgetplanner.dto.BudgetBalanceResponseDto;
import com.maranata.budgetplanner.dto.UpdateBudgetDto;
import com.maranata.budgetplanner.entity.Budget;
import com.maranata.budgetplanner.entity.BudgetPeriodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("budget")
public class BudgetController {

    @Autowired
    BudgetService budgetService;
    @Autowired
    BudgetFeignClient budgetFeignClient;

    @GetMapping("/list")
    public Collection<Budget> budgetList(Model model) {
        model.addAttribute("budgets", budgetService.budgetList().getBody());
        return budgetService.budgetList().getBody();
    }

    @GetMapping("/balances")
    public ResponseEntity<List<BudgetBalanceResponseDto>> getBudgetsWithBalances(@RequestParam Long validationId,
                                                                                 @RequestParam BudgetPeriodType periodType,
                                                                                 @RequestParam String startDate,
                                                                                 @RequestParam Locale locale) {
        return ResponseEntity.ok(budgetService.getBudgetsBalances(validationId, periodType, LocalDate.parse(startDate), locale));
    }

    @GetMapping("/list/{categoriaId}")
    public ResponseEntity<List<Budget>> findByCategoria(@PathVariable Long categoriaId){
        return new ResponseEntity<>(budgetFeignClient.findByCategory(categoriaId),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id,@RequestBody UpdateBudgetDto budgetDto){
        return new ResponseEntity<>(budgetService.update(id,budgetDto),HttpStatus.OK);
    }

}

