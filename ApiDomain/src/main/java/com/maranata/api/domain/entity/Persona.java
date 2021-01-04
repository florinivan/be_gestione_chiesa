package com.maranata.api.domain.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name="persone")

public class Persona {

    @Id
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
    @JoinColumn(name="indirizzo_residenza",referencedColumnName = "id_ind_residenza")
    private IndirizzoResidenza indirizzoResidenza;

}



