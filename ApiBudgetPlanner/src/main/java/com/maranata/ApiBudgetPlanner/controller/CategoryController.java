package com.maranata.ApiBudgetPlanner.controller;

import com.maranata.ApiBudgetPlanner.service.CategoryService;
import com.maranata.budgetplanner.entity.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/summary")
    ResponseEntity<List<Point>> findSummary(){
        return new ResponseEntity<>(categoryService.findUsageByCategory(), HttpStatus.OK);
    }
}
