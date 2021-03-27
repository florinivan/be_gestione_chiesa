package com.maranata.budgetplanner.dto;

import lombok.Data;

@Data
public class TransactionResponseDto {

    private Long id;
    private Long budgetId;
    private Double amount;
    private String description;
    private String date;
    private String tags;
    private Long categoryId;
    private Long validazionId;

}
