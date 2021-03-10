package com.maranata.budgetplanner.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="flusso_validazione")
public class FlussoValidazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="id_utente")
    private Long idUtente;

    @ManyToOne
    @JoinColumn (name="stato_validazione_id")
    private StatoValidazione stato;

}
