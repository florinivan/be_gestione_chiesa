package com.maranata.budgetplanner.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private CategoryType type;
}

