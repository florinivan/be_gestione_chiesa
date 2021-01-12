package com.maranata.Authentication.entity;

import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table(name= "accounts")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="id_persona")
    private String idPersona;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;
}
