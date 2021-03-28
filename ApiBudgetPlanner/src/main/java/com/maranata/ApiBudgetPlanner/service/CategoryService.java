package com.maranata.ApiBudgetPlanner.service;

import com.maranata.ApiBudgetPlanner.feign.BudgetFeignClient;
import com.maranata.budgetplanner.entity.*;
import com.maranata.budgetplanner.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    BudgetFeignClient budgetFeignClient;

    public List<Point> findUsageByCategory() {
        List<Point> riassunti = new ArrayList<>();
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusMonths(1);
        List<Budget> budgets = budgetFeignClient.findByRange(DateUtil.getMiliseconds(start), DateUtil.getMiliseconds(end));
        Map<Category, List<Budget>> groups = budgets
                .stream()
                .filter(b -> b.getCategory().getType() == CategoryType.EXPENSE)
                .collect(Collectors.groupingBy(Budget::getCategory));
        for (Map.Entry<Category, List<Budget>> entry : groups.entrySet()) {
            double total = entry.getValue()
                    .stream()
                    .mapToDouble(Budget::getActual)
                    .sum();
            Point riassunto = new Point(entry.getKey().getDescription(), entry.getKey().getId(), total, PointType.CATEGORY);
            riassunti.add(riassunto);
        }

        riassunti.sort((p1, p2) -> Double.compare(p2.getKey(), p1.getValue()));
        return riassunti;
    }


    public List<Point> findMonthlyTransactionUsage() {
        List<Point> points = new ArrayList<>();
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusMonths(6);
        List<Budget> budgets = budgetFeignClient.findByRange(DateUtil.getMiliseconds(start), DateUtil.getMiliseconds(end));

        // group by period
        Map<Long, List<Budget>> groups = budgets
                .stream()
                .collect(Collectors.groupingBy(Budget::getEndDate, TreeMap::new, Collectors.toList()));

        LocalDate now = LocalDate.now();
        // populate empty months, if any

        // generate points
        for (Map.Entry<Long, List<Budget>> entry : groups.entrySet()) {
            // budget
            double budget = entry.getValue()
                    .stream()
                    .filter(b -> b.getCategory().getType() == CategoryType.EXPENSE)
                    .mapToDouble(Budget::getProjected)
                    .sum();

            // spending
            double spending = entry.getValue()
                    .stream()
                    .filter(b -> b.getActual() > 0)
                    .filter(b -> b.getCategory().getType() == CategoryType.EXPENSE)
                    .mapToDouble(Budget::getActual)
                    .sum();

            // refund
            double refund = entry.getValue()
                    .stream()
                    .filter(b -> b.getActual() < 0)
                    .mapToDouble(Budget::getActual)
                    .sum();

            String month = DateUtil.toFriendlyMonthDisplay(entry.getKey());
            Point spendingPoint = new Point(month, entry.getKey(), spending, PointType.MONTHLY_SPEND);
            Point refundPoint = new Point(month, entry.getKey(), refund, PointType.MONTHLY_REFUND);
            Point budgetPoint = new Point(month, entry.getKey(), budget, PointType.MONTHLY_BUDGET);

            points.add(spendingPoint);
            points.add(refundPoint);
            points.add(budgetPoint);
        }
        return points;
    }

}
