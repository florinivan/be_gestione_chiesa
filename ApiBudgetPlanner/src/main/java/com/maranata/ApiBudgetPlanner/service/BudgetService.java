package com.maranata.ApiBudgetPlanner.service;

import com.maranata.ApiBudgetPlanner.feign.BudgetFeignClient;
import com.maranata.ApiBudgetPlanner.feign.TransazioneFeignClient;
import com.maranata.budgetplanner.entity.Budget;
import com.maranata.budgetplanner.entity.PeriodRange;
import com.maranata.budgetplanner.entity.TipoTransazione;
import com.maranata.budgetplanner.entity.Transazione;
import com.maranata.budgetplanner.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Service
public class BudgetService {

    @Autowired
    TransazioneFeignClient transazioneFeignClient;
    @Autowired
    BudgetFeignClient budgetFeignClient;

    public ResponseEntity<Collection<Budget>> budgetList(){
        return budgetFeignClient.budgetList();
    }

    private Double calculateBudgetBalance(Budget budget,TipoTransazione tipo, LocalDate startDate, Locale locale) {
        Double totalExpense = getTransactionsByBudgetAndPeriod(budget,tipo,startDate,locale)
                .stream()
                .mapToDouble(item -> item.getImportoStimato())
                .sum();

        return budget.getImportoStimato() - totalExpense;
    }

    public List<Transazione> getTransactionsByBudgetAndPeriod(Budget budget, TipoTransazione tipo, LocalDate startDate, Locale locale) {
        PeriodRange periodRange = getMonthPeriodRange(startDate,locale);

        return  transazioneFeignClient.getTransactionsByTipoAndDateBetween(
                tipo,
                budget.getId(),
                budget.getFlusso().getId(),
                DateUtil.getMiliseconds(periodRange.getStartDate()),
                DateUtil.getMiliseconds(periodRange.getEndDate()));

    }

    public PeriodRange getMonthPeriodRange(LocalDate date,  Locale locale) {
        LocalDate startDate = date.with(firstDayOfMonth());
        LocalDate endDate = date.with(lastDayOfMonth());

        return new PeriodRange(startDate,endDate);
    }
}
