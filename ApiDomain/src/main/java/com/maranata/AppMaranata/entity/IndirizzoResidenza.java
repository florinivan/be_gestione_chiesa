package com.maranata.AppMaranata.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "indirizzo_residenza")

public class IndirizzoResidenza {

    @Id
    @Column(name ="ID_IND_RESIDENZA")
    private int id;

    @Column(name = "VIA")
    private String via;

    @Column(name = "COMUNE")
    private String comune;

    @Column(name = "PROVINCIA")
    private String provincia;

    @Column(name = "REGIONE")
    private String regione;

    @Column(name = "PAESE")
    private String paese;
}
