package com.maranata.BudgetPlanner.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="categorie")
public class Categoria {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_descrizione")
    private Long id;

    @Column(name="descrizione")
    private String descrizione;
}
