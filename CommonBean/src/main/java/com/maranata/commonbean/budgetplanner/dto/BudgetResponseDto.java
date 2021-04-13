package com.maranata.commonbean.budgetplanner.dto;

import lombok.Data;

@Data
public class BudgetResponseDto {

    private Long id;
    private Double projected;
    private String periodType;

}
