package com.maranata.Api.dto;

import lombok.Data;

@Data
public class IndirizzoResidenzaDto {

    private String comune;
    private String paese;
    private String provincia;
    private String regione;
    private String via;
}
