package com.maranata.budgetplanner.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Long startDate;

    private Long endDate;

    private String tags;

    private double projected;

    private double actual;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @OneToOne
    @JoinColumn(name="validation_id")
    private Validation validation;

}

