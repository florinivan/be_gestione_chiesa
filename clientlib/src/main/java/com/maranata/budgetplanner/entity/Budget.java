package com.maranata.budgetplanner.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
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

    private double importoStimato;

    private double importoAttuale;

    @Enumerated(EnumType.STRING)
    private PeriodoBudget periodo;

    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria;

    @OneToOne
    @JoinColumn(name="flusso_id")
    private  FlussoValidazione flusso;

}

