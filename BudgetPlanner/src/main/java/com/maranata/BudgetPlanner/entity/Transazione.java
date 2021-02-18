package com.maranata.BudgetPlanner.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="transazioni")
public class Transazione {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;

   @Column(name="data")
   public Date data;

   @Column(name ="somma")
   public double somma;

   @Column(name = "descrizione")
   public String descrizione;

   @ManyToOne
   @JoinColumn(name="categoria",referencedColumnName = "id_descrizione")
   private Categoria categoria;

   public enum TIPOLOGIA {SPESE,ENTRATE,BUDGET};


}
