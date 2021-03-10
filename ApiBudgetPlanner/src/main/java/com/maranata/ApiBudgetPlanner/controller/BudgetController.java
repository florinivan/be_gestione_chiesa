package com.maranata.ApiBudgetPlanner.controller;

import com.maranata.ApiBudgetPlanner.service.BudgetService;
import com.maranata.budgetplanner.dto.BudgetBalanceResponseDto;
import com.maranata.budgetplanner.entity.Budget;
import com.maranata.budgetplanner.entity.PeriodoBudget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("budget")
public class BudgetController {

    @Autowired
    BudgetService budgetService;

    @GetMapping("/list")
    public Collection<Budget> budgetList(Model model) {
        model.addAttribute("budgets", budgetService.budgetList().getBody());
        return budgetService.budgetList().getBody();
    }



    @GetMapping("/balances")
    public ResponseEntity<List<BudgetBalanceResponseDto>> getBudgetsWithBalances(
            @RequestParam Long flussoId,
            @RequestParam PeriodoBudget periodo,
            @RequestParam String startDate,
            @RequestParam Locale locale
           ){
        return ResponseEntity.ok(budgetService.getBudgetsBalances(flussoId,periodo,LocalDate.parse(startDate),locale));
    }

}

