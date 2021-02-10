package com.maranata.Api.dto;

import lombok.Data;


@Data
public class MembroDto {

    private PersonaDto persona;
    private String email;
    private String telefono;
    private int dataBattesimo;
    private String luogoBattesimo;
    private String responsabileBattesimo;
    private int trasferito;
    private boolean flgVedovo;
    private boolean flgDivorziato;




}
