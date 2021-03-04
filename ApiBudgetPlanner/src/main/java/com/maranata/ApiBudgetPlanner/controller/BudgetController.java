package com.maranata.ApiBudgetPlanner.controller;

import com.maranata.ApiBudgetPlanner.service.BudgetService;
import com.maranata.budgetplanner.entity.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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
}
