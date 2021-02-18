package com.maranata.Api.dto;

import lombok.Data;

@Data
public class PersonaDto {

    private Long id;
    private String codiceFiscale;
    private String nome;
    private String cognome;
    private String dataNascita;
    private ResidenzaDto residenza;

}
