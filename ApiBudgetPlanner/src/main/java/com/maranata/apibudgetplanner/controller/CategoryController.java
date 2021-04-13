package com.maranata.apibudgetplanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maranata.apibudgetplanner.service.CategoryService;
import com.maranata.commonbean.budgetplanner.entity.Point;

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
