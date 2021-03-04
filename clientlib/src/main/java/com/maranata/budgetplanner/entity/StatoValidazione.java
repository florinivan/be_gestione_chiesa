package com.maranata.budgetplanner.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="stato_validazione")
public class StatoValidazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descrizione;
}
