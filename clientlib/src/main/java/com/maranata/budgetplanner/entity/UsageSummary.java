package com.maranata.budgetplanner.entity;

public class UsageSummary {

    private double income;
    private double projected;
    private double actual;

    public UsageSummary(double income, double projected, double actual) {
        this.income = income;
        this.projected = projected;
        this.actual = actual;
    }

    public double getIncome() {
        return income;
    }

    public double getProjected() {
        return projected;
    }

    public double getActual() {
        return actual;
    }

    public double getRemaining() {
        return projected - actual;
    }
}
