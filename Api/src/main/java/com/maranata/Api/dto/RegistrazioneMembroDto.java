package com.maranata.Api.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegistrazioneMembroDto extends MembroDto{

    private ConiugeDto coniugeDto;
    private List<BambinoDto> bambini;


}
