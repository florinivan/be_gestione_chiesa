package com.maranata.BudgetPlanner.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="stato_validazione")
public class StatoValidazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_stato")
    private Long id;

    @Column(name="descrizione")
    private String descrizione;
}
