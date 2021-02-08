package com.maranata.api.domain.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name="persone")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name = "ID_CODICE_FISCALE")
    private String codiceFiscale;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "COGNOME")
    private String cognome;

    @Column(name = "DATA_NASCITA")
    private String dataNascita;

    @Column(name = "MASCHIO_FEMMINA")
    private String maschioFemmina;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="residenza",referencedColumnName = "id_residenza")
    private Residenza residenza;

}



