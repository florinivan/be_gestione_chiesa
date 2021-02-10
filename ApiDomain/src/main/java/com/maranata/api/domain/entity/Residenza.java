package com.maranata.api.domain.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name ="residenza")
public class Residenza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID_RESIDENZA")
    private long id;

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
