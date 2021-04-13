package com.maranata.apibudgetplanner.service;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maranata.apibudgetplanner.feign.BudgetFeignClient;
import com.maranata.apibudgetplanner.feign.TransactionFeignClient;
import com.maranata.commonbean.budgetplanner.dto.TransactionResponseDto;
import com.maranata.commonbean.budgetplanner.entity.Budget;
import com.maranata.commonbean.budgetplanner.entity.CategoryType;
import com.maranata.commonbean.budgetplanner.entity.Point;
import com.maranata.commonbean.budgetplanner.entity.PointType;
import com.maranata.commonbean.budgetplanner.entity.Transaction;
import com.maranata.commonbean.budgetplanner.entity.UsageSummary;
import com.maranata.commonbean.budgetplanner.util.DateUtil;


@Service
public class TransactionService {

    @Autowired
    TransactionFeignClient transactionFeignClient;
    @Autowired
    BudgetFeignClient budgetFeignClient;

    public ResponseEntity<Collection<Transaction>> transazioniList() {
        return transactionFeignClient.transactionList();
    }

    /**
     * add transaction and update budget
     *
     * @param transaction
     * @param budgetId
     * @return
     */
    public TransactionResponseDto addTransaction(Transaction transaction, Long budgetId) {

        List<Budget> budgets = budgetFeignClient.findByCategory(transaction.getCategory().getId());

        Optional<Budget> optionalBudget = budgets.stream().filter(bg -> bg.getId() == budgetId).findFirst();
        // get data from optional or throw exception if not present
        Budget budget = Optional.ofNullable(optionalBudget.get()).orElseThrow(() -> new RuntimeException("No data!"));
        // add transaction if type SPESE -> value * -1
        if (transaction.getCategory().getType().equals(CategoryType.EXPENSE)) {
            transaction.setAmount(transaction.getAmount() * -1);
        }
        transactionFeignClient.addTransaction(transaction);
        // calculate actual import budget
        budget.setActual(budget.getActual() + transaction.getAmount());
        // update budget
        budgetFeignClient.updateBudget(budgetId, budget);

        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactionResponseDto.setAmount(transaction.getAmount());
        transactionResponseDto.setCategoryId(transaction.getCategory().getId());
        transactionResponseDto.setDate(transaction.getDate().toString());
        transactionResponseDto.setDescription(transaction.getDescription());
        transactionResponseDto.setValidazionId(transaction.getValidation().getId());
        transactionResponseDto.setTags(transaction.getTags());
        transactionResponseDto.setBudgetId(budget.getId());

        return transactionResponseDto;
    }

    private static final DateTimeFormatter SUMMARY_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM");

    public List<Point> findTransactionUsage() {
        LocalDate now = LocalDate.now();

        List<Point> points = new ArrayList<>();

        LocalDate begin = LocalDate.of(now.getYear(), now.getMonth(), 1);

        LocalDate ending = now;

        // first 1-7 days show last 7 days's transactions
        if (ending.getDayOfMonth() > 7) {
            begin = begin.minusDays(7 - ending.getDayOfMonth());
        }

        List<Transaction> transazioni = transactionFeignClient.findByRange(DateUtil.getMiliseconds(begin), DateUtil.getMiliseconds(ending));

        Map<Long, List<Transaction>> groups = transazioni
                .stream()
                .collect(Collectors.groupingBy(Transaction::getDate, TreeMap::new, Collectors.toList()));

        int days = Period.between(begin, ending).getDays() + 1;

        for (int i = 0; i < days; i++) {
            LocalDate day = begin.plusDays(i);
            groups.putIfAbsent(DateUtil.getMiliseconds(day), Collections.emptyList());
        }


        for (Map.Entry<Long, List<Transaction>> entry : groups.entrySet()) {
            double total = entry.getValue()
                    .stream()
                    .mapToDouble(Transaction::getAmount)
                    .sum();
            LocalDate res = DateUtil.toLocalDate(entry.getKey());
            Point point = new Point(SUMMARY_DATE_FORMATTER.format(res), entry.getKey().longValue(), total, PointType.TRANSACTIONS);
            points.add(point);
        }
        return points;
    }


    public UsageSummary findUsageSummary(Integer month,Integer year) {

        LocalDate start = DateUtil.toLocalDateBy(month,year);
        LocalDate end = start.plusMonths(1);
        if(month==0){
            end = DateUtil.toLocalDateBy(12,year);
        }
        List<Budget> budgets = budgetFeignClient.findByRange(DateUtil.getMiliseconds(start), DateUtil.getMiliseconds(end));

        double income =
                budgets
                        .stream()
                        .filter(p -> p.getCategory().getType() == CategoryType.INCOME)
                        .mapToDouble(Budget::getActual)
                        .sum();

        double budget =
                budgets
                        .stream()
                        .filter(p -> p.getCategory().getType() == CategoryType.EXPENSE)
                        .mapToDouble(Budget::getProjected)
                        .sum();

        double spent =
                budgets
                        .stream()
                        .filter(p -> p.getCategory().getType() == CategoryType.EXPENSE)
                        .mapToDouble(Budget::getActual)
                        .sum();
        return new UsageSummary(income, budget, spent);
    }


}
