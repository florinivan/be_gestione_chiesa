package com.maranata.commonbean.budgetplanner.dto;

import lombok.Data;

@Data
public class UpdateBudgetDto {

    private Long id;
    private String name;
    private double projected;
    private double actual;
}
