package com.maranata.budgetplanner.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BudgetBalanceResponseDto extends BudgetResponseDto{

    private Double importoAttuale;
    private LocalDate startDate;
    private LocalDate endDate;

}

