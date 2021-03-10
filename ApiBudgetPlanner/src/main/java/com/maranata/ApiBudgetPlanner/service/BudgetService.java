package com.maranata.ApiBudgetPlanner.service;

import com.maranata.ApiBudgetPlanner.feign.BudgetFeignClient;
import com.maranata.ApiBudgetPlanner.feign.TransazioneFeignClient;
import com.maranata.budgetplanner.dto.BudgetBalanceResponseDto;
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
    TransazioneFeignClient transazioneFeignClient;
    @Autowired
    BudgetFeignClient budgetFeignClient;

    public ResponseEntity<Collection<Budget>> budgetList(){
        return budgetFeignClient.budgetList();
    }

    public List<BudgetBalanceResponseDto> getBudgetsBalances(Long flussoId, PeriodoBudget periodo, LocalDate startDate,Locale locale) {
        List<Budget> budgetList = budgetFeignClient.findAllByFlussoAndPeriodo(flussoId, periodo.toString());

        return budgetList
                .stream()
                .map(item -> convertToBudgetBalanceResponseDto(item,TipoTransazione.SPESE,startDate,locale))
                .collect(Collectors.toList());
    }


    private BudgetBalanceResponseDto convertToBudgetBalanceResponseDto(Budget budget,TipoTransazione tipo, LocalDate startDate, Locale locale) {
        BudgetBalanceResponseDto budgetBalanceResponseDto = new BudgetBalanceResponseDto();
        budgetBalanceResponseDto.setImportoAttuale(calculateBudgetBalance(budget,tipo, startDate,locale));
        budgetBalanceResponseDto.setId(budget.getId());
        budgetBalanceResponseDto.setImportoStimato(budget.getImportoStimato());
        budgetBalanceResponseDto.setPeriodType(budget.getPeriodo().name());


        PeriodRange periodRange = getPeriodRange(budget.getPeriodo(), startDate,locale);
        budgetBalanceResponseDto.setStartDate(periodRange.getStartDate());
        budgetBalanceResponseDto.setEndDate(periodRange.getEndDate());

        return budgetBalanceResponseDto;
    }

    private Double calculateBudgetBalance(Budget budget,TipoTransazione tipo, LocalDate startDate, Locale locale) {
        Double totalExpense = getTransactionsByBudgetAndPeriod(budget,tipo,startDate,locale)
                .stream()
                .mapToDouble(item -> item.getImportoStimato())
                .sum();

        return budget.getImportoStimato() - totalExpense;
    }

    public List<Transazione> getTransactionsByBudgetAndPeriod(Budget budget, TipoTransazione tipo, LocalDate startDate, Locale locale) {
        PeriodRange periodRange = getPeriodRange(budget.getPeriodo(),startDate,locale);

        return  transazioneFeignClient.getTransactionsByTipoAndDateBetween(
                tipo,
                budget.getFlusso().getId(),
                DateUtil.getMiliseconds(periodRange.getStartDate()),
                DateUtil.getMiliseconds(periodRange.getEndDate()));

    }

    private PeriodRange getPeriodRange(PeriodoBudget periodo, LocalDate date,Locale locale) {
        PeriodRange periodRange;

        switch (periodo) {
            case WEEKLY:
                periodRange = getWeekPeriodRange(date,locale);
                break;
            case MONTHLY:
                periodRange = getMonthPeriodRange(date,locale);
                break;
            case YEARLY:
                periodRange = getYearPeriodRange(date,locale);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + periodo);
        }

        return periodRange;
    }

    private PeriodRange getWeekPeriodRange(LocalDate date,Locale locale) {
        LocalDate startDate = date.with(WeekFields.of(locale).dayOfWeek(), 1L);
        LocalDate endDate = date.with(WeekFields.of(locale).dayOfWeek(), 7L);

        return new PeriodRange(startDate, endDate);
    }

    private PeriodRange getMonthPeriodRange(LocalDate date,Locale locale) {
        LocalDate startDate = date.with(firstDayOfMonth());
        LocalDate endDate = date.with(lastDayOfMonth());

        return new PeriodRange(startDate, endDate);
    }

    private PeriodRange getYearPeriodRange(LocalDate date,Locale locale) {
        LocalDate startDate = date.with(firstDayOfYear());
        LocalDate endDate = date.with(lastDayOfYear());

        return new PeriodRange(startDate, endDate);
    }
}
