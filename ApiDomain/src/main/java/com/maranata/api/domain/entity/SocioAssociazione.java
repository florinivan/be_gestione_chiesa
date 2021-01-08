package com.maranata.api.domain.entity;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;

@Data
@Entity
@Table(name = "soci_associazione")

public class SocioAssociazione implements Serializable{

    @Id
    @Column(name="id_socio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSocio;

    @ManyToOne
    @JoinColumn(name="CODICE_FISCALE",referencedColumnName = "ID_CODICE_FISCALE")
    private Persona persona;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "ANNOTAZIONI")
    private String annotazioni;

    @Column(name = "DATA_INSCRIZIONE")
    private int dataInscrizione;

    @Column(name = "FLAG_STATUTO")
    private String flagStatuto;

    @Column(name = "FLAG_CD")
    private String flagCd;

}
