package com.maranata.budgetplanner.dto;

import lombok.Data;

@Data
public class BudgetResponseDto {

    private Long id;
    private Double importoStimato;
    private String periodType;

}
