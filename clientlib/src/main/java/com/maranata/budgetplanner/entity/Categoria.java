package com.maranata.budgetplanner.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="categorie")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descrizione;

    @Enumerated(EnumType.STRING)
    private TipoCategoria tipoCategoria;
}
//insieme di spese : intrari alle bisericii
