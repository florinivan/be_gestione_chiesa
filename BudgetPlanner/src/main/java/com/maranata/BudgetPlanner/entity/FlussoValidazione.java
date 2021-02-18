package com.maranata.BudgetPlanner.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="flusso_validazioni")
public class FlussoValidazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name ="id_utente")
    private Long idUtente;

    @Column(name="id_utente_responsabile")
    private Long idUtenteResponsabile;

    @ManyToOne
    @JoinColumn (name="stato_validazione", referencedColumnName = "id_stato")
    private StatoValidazione statoValidazione;
}
