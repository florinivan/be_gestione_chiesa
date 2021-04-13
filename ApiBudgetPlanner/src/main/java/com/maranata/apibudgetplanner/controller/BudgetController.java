package com.maranata.apibudgetplanner.controller;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maranata.apibudgetplanner.feign.BudgetFeignClient;
import com.maranata.apibudgetplanner.service.BudgetService;
import com.maranata.commonbean.budgetplanner.dto.BudgetBalanceResponseDto;
import com.maranata.commonbean.budgetplanner.dto.UpdateBudgetDto;
import com.maranata.commonbean.budgetplanner.entity.Budget;
import com.maranata.commonbean.budgetplanner.entity.BudgetPeriodType;

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

