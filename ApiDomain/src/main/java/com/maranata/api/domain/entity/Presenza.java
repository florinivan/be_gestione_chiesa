package com.maranata.api.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "presenze")
public class Presenza implements Serializable {

    @EmbeddedId
    private PresenzaId presenzaId;

    @Column(name = "NUMERO_BAMBINI_14")
    private int numeroBambini14;

    @Column(name = "TELEFONO")
    private String telefono;

    @Temporal( TemporalType.TIMESTAMP )
    @CreationTimestamp
    @Column (name = "crea_data")
    private Date creaData;
}
