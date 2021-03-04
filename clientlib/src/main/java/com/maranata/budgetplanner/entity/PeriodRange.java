package com.maranata.budgetplanner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PeriodRange {

    private LocalDate startDate;
    private LocalDate endDate;
}
