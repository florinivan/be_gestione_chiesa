package com.maranata.commonbean.budgetplanner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Point {

    private String label;
    private long key;
    private double value;
    private PointType pointType;
}
