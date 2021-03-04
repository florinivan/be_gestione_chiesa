package com.maranata.budgetplanner.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="transazioni")
public class  Transazione {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private Long data;

   private double somma;

   private String descrizione;

   private float importoStimato;

   private float importoAttuale;

   private String tags;

   @ManyToMany(mappedBy = "budgetTransazioni")
   Set<Budget> budgets;

   @ManyToOne
   @JoinColumn(name="categoria_id")
   private Categoria categoria;

   @Enumerated(EnumType.STRING)
   private TipoTransazione tipo;

   @ManyToOne
   @JoinColumn(name="flusso_id")
   private FlussoValidazione flusso;

}
