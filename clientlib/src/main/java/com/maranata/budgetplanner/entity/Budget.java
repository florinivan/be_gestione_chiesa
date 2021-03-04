package com.maranata.budgetplanner.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private Long dataInizio;

    private Long dataFine;

    private String tags;

    private float importoStimato;

    private float importoAttuale;

    @ManyToMany
    @JoinTable(
            name="budget_transazioni",
            joinColumns = @JoinColumn(name = "budget_id"),
            inverseJoinColumns = @JoinColumn(name = "transazioni_id"))
    private Set<Transazione> budgetTransazioni;

    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria;

    @OneToOne
    @JoinColumn(name="flusso_id")
    private  FlussoValidazione flusso;



}
