package com.maranata.budgetplanner.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="transactions")
public class Transaction {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private Long date;

   private String description;

   private Double amount;

   private String tags;

   @ManyToOne
   @JoinColumn(name="category_id")
   private Category category;

   @ManyToOne
   @JoinColumn(name="validation_id")
   private Validation validation;

}
