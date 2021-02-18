package com.maranata.Api.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegistrazioneMembroDto {

    private MembroDto membroDto;
    private ConiugeDto coniugeDto;
    private List<BambinoDto> bambini;


}
