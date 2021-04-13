package com.maranata.domainbudgetplanner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maranata.commonbean.budgetplanner.entity.Budget;
import com.maranata.commonbean.budgetplanner.entity.Category;
import com.maranata.commonbean.budgetplanner.entity.Validation;
import com.maranata.domainbudgetplanner.dao.BudgetRepository;

@Service
public class BudgetService {

    @Autowired
    CategoryService categoryService;
    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    ValidationService validationService;

    public List<Budget> findAll() {
        return budgetRepository.findAll();
    }

    public List<Budget> findByCategory(Long categoryId) {
        return budgetRepository.findBycategory(categoryId);
    }

    public Optional<Budget> findById(Long id) {
        return budgetRepository.findById(id);
    }

    public List<Budget> findByDate(Long startDate, Long endDate){
        return budgetRepository.findByDate(startDate,endDate);
    }

    public List<Budget> findAllByValidationAndPeriod(Long validationId, Long startDate, Long endDate) {
        return budgetRepository.findAllByValidationAndPeriod(validationId, startDate, endDate);
    }

    @Transactional
    public Budget addBudget(Budget budget) {
        Optional<Category> category = categoryService.findByDescription(budget.getCategory().getDescription());
        List<Validation> validations = validationService.findByUserId(budget.getValidation().getUserId());
        if (category.isPresent() || !validations.isEmpty()) {
            try {
                budgetRepository.save(budget);
            } catch (Exception e) {
                //TOOD add custom exception
                throw e;
            }
        } else {
            //TOOD add custom exception
            throw new RuntimeException();
        }
        return budget;
    }
    public Budget update(Long id,Budget newBudget) {
        Optional<Budget> optionalBudget = budgetRepository.findById(id);
        Budget budget = Optional.ofNullable(optionalBudget.get()).orElseThrow(() -> new RuntimeException("No data!"));
        budget.setName(newBudget.getName());
        budget.setStartDate(newBudget.getStartDate());
        budget.setTags(newBudget.getTags());
        budget.setCategory(newBudget.getCategory());
        budget.setEndDate(newBudget.getEndDate());
        budget.setActual(newBudget.getActual());
        budget.setProjected(newBudget.getProjected());
        budget.setValidation(newBudget.getValidation());
        Budget updateBudget = budgetRepository.save(budget);
        return updateBudget;
    }

    public void deleteById(Long id) {
        budgetRepository.deleteById(id);
    }

}
