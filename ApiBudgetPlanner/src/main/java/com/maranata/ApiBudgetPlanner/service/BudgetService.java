package com.maranata.ApiBudgetPlanner.service;

import com.maranata.ApiBudgetPlanner.feign.BudgetFeignClient;
import com.maranata.ApiBudgetPlanner.feign.CategoryFeignClient;
import com.maranata.ApiBudgetPlanner.feign.TransactionFeignClient;
import com.maranata.budgetplanner.dto.BudgetBalanceResponseDto;
import com.maranata.budgetplanner.dto.UpdateBudgetDto;
import com.maranata.budgetplanner.entity.*;
import com.maranata.budgetplanner.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.*;

@Service
public class BudgetService {

    @Autowired
    TransactionFeignClient transactionFeignClient;
    @Autowired
    BudgetFeignClient budgetFeignClient;
    @Autowired
    CategoryFeignClient categoryFeignClient;

    public ResponseEntity<Collection<Budget>> budgetList() {
        return budgetFeignClient.budgetList();
    }

    public Budget update(Long id, UpdateBudgetDto updateBudget) {
        Optional<Budget> optionalBudget = budgetFeignClient.findById(id);
        Optional<Category> categoria = categoryFeignClient.findById(optionalBudget.get().getCategory().getId());
        optionalBudget.get().setName(updateBudget.getName());
        optionalBudget.get().setProjected(updateBudget.getProjected());

        // INCOME type allow user change actual without
        // add transactions
        if (categoria.get().getType() == CategoryType.INCOME) {
            optionalBudget.get().setActual(updateBudget.getActual());
        }
        budgetFeignClient.updateBudget(id,optionalBudget.get());
        return optionalBudget.get();
    }

    public List<BudgetBalanceResponseDto> getBudgetsBalances(Long validationId, BudgetPeriodType periodType, LocalDate startDate, Locale locale) {
        PeriodRange periodRange = getPeriodRange(periodType, startDate, locale);
        List<Budget> budgetList = budgetFeignClient.findAllByRange(validationId, DateUtil.getMiliseconds(periodRange.getStartDate()),
                DateUtil.getMiliseconds(periodRange.getEndDate()));

        return budgetList
                .stream()
                .map(item -> convertToBudgetBalanceResponseDto(item, CategoryType.EXPENSE, periodRange, periodType, startDate, locale))
                .collect(Collectors.toList());
    }


    private BudgetBalanceResponseDto convertToBudgetBalanceResponseDto(Budget budget, CategoryType type, PeriodRange periodRange, BudgetPeriodType periodType, LocalDate startDate, Locale locale) {
        BudgetBalanceResponseDto budgetBalanceResponseDto = new BudgetBalanceResponseDto();
        budgetBalanceResponseDto.setActual(calculateBudgetBalance(budget, type, periodRange));
        budgetBalanceResponseDto.setId(budget.getId());
        budgetBalanceResponseDto.setProjected(budget.getProjected());
        budgetBalanceResponseDto.setPeriodType(periodType.name());

        budgetBalanceResponseDto.setStartDate(periodRange.getStartDate());
        budgetBalanceResponseDto.setEndDate(periodRange.getEndDate());

        return budgetBalanceResponseDto;
    }

    private Double calculateBudgetBalance(Budget budget, CategoryType type, PeriodRange periodRange) {
        Double totalExpense = getTransactionsByBudgetAndPeriod(budget, periodRange, type)
                .stream()
                .mapToDouble(item -> item.getAmount())
                .sum();

        return budget.getProjected() - totalExpense;
    }

    public List<Transaction> getTransactionsByBudgetAndPeriod(Budget budget, PeriodRange periodRange, CategoryType type) {

        return transactionFeignClient.getTransactionsByTypeAndDateBetween(
                type,
                budget.getValidation().getId(),
                DateUtil.getMiliseconds(periodRange.getStartDate()),
                DateUtil.getMiliseconds(periodRange.getEndDate()));

    }

    private PeriodRange getPeriodRange(BudgetPeriodType periodType, LocalDate date, Locale locale) {
        PeriodRange periodRange;

        switch (periodType) {
            case WEEKLY:
                periodRange = getWeekPeriodRange(date, locale);
                break;
            case MONTHLY:
                periodRange = getMonthPeriodRange(date, locale);
                break;
            case YEARLY:
                periodRange = getYearPeriodRange(date, locale);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + periodType);
        }

        return periodRange;
    }

    private PeriodRange getWeekPeriodRange(LocalDate date, Locale locale) {
        LocalDate startDate = date.with(WeekFields.of(locale).dayOfWeek(), 1L);
        LocalDate endDate = date.with(WeekFields.of(locale).dayOfWeek(), 7L);

        return new PeriodRange(startDate, endDate);
    }

    private PeriodRange getMonthPeriodRange(LocalDate date, Locale locale) {
        LocalDate startDate = date.with(firstDayOfMonth());
        LocalDate endDate = date.with(lastDayOfMonth());

        return new PeriodRange(startDate, endDate);
    }

    private PeriodRange getYearPeriodRange(LocalDate date, Locale locale) {
        LocalDate startDate = date.with(firstDayOfYear());
        LocalDate endDate = date.with(lastDayOfYear());

        return new PeriodRange(startDate, endDate);
    }
}
