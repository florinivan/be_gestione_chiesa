package com.maranata.api.domain.entity;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;

@Data
@Entity
@Table(name = "bambini")
public class Bambino  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_bambino")
    private long idBambino;

    @ManyToOne
    @JoinColumn(name="CODICE_FISCALE",referencedColumnName = "ID_CODICE_FISCALE")
    private Persona persona;

    @Column(name="CODICE_FISCALE_PADRE")
    private String codiceFiscalePadre;

    @Column(name="CODICE_FISCALE_MADRE")
    private String codiceFiscaleMadre;


}
