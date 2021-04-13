package com.maranata.commonbean.budgetplanner.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="validation_types")
public class ValidationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
}
