package com.maranata.api.domain.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "membri")

public class Membro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_membro")
    private long idMembro;

    @ManyToOne
    @JoinColumn(name="CODICE_FISCALE",referencedColumnName = "ID_CODICE_FISCALE")
    private Persona persona;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "CF_CONIUGE")
    private String cfConiuge;

    @Column(name = "DATA_BATTESIMO")
    private int dataBattesimo;

    @Column(name = "LUOGO_BATTESIMO")
    private String luogoBattesimo;

    @Column(name = "RESPONSABILE_BATTESIMO")
    private String responsabileBattesimo;

    @Column(name = "TRASFERITO")
    private int trasferito;

    @Column(name = "ATTIVO_PASSIVO")
    private String attivoPassivo;

    @Column(name = "SOTTODISCIPLINA")
    private String sottodisciplina;

    @Column(name = "DURATA_DISCIPLINA")
    private int durataDisciplina;

    @Column(name = "PREGHIERA")
    private String preghiera;

    @Column(name = "SERMONE")
    private String sermone;

    @Column(name = "CORO_LODE")
    private String coroLode;

    @Column(name = "P_SCUOLA_DOMENICALE")
    private String p_scuola_domenicale;

    @Column(name = "CENA_DEL_SIGNORE")
    private String cenaDelSignore;

}
