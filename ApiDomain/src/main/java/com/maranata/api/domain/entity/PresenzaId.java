package com.maranata.api.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PresenzaId implements Serializable {

    @Column(name= "NOME_COGNOME")
    public String nomeCognome;

    @Column(name = "DATA_PRESENZA")
    public int dataPresenza;
}
