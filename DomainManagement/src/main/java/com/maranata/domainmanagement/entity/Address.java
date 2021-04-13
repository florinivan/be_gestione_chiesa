package com.maranata.domainmanagement.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name ="adress")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String street;

    private String city;

    private String district;

    private String region;

    private String country;
}
