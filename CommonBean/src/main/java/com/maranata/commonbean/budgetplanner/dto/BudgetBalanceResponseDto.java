package com.maranata.commonbean.budgetplanner.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BudgetBalanceResponseDto extends BudgetResponseDto{

    private Double actual;
    private LocalDate startDate;
    private LocalDate endDate;

}

