package com.maranata.Api.service;

import com.maranata.Api.dto.BambinoDto;
import com.maranata.Api.dto.MembroDto;
import com.maranata.Api.dto.PersonaDto;
import com.maranata.Api.feign.client.BambinoFeignClient;
import com.maranata.Api.feign.client.MembroFeignClient;
import com.maranata.Api.feign.client.PersonaFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RegistrazioneMembroService {

    @Autowired
    MembroFeignClient membroFeignClient;
    @Autowired
    PersonaFeignClient personaFeignClient;
    @Autowired
    BambinoFeignClient bambinoFeignClient;

    public MembroDto registrazioneMembro(MembroDto membroDto,String codiceFiscale){
        if(membroDto!=null){
        membroCheck(codiceFiscale);
        return membroDto;}
        else return null;

    }

    public Boolean membroCheck(String codiceFiscale){
        return membroFeignClient.checkMembro(codiceFiscale);
    }

    public Boolean bambinoCheck(String codiceFiscale){
        return bambinoFeignClient.checkBambino(codiceFiscale);
    }
    
    public ResponseEntity<Collection<PersonaDto>> findBycodiceFiscale(String codiceFiscale){
        return personaFeignClient.findBycodiceFiscale(codiceFiscale);
    }

    public PersonaDto personaAdd (PersonaDto personaDto){
        return personaFeignClient.addPersona(personaDto);
    }

    public MembroDto membroAdd(MembroDto membroDto){
        return membroFeignClient.membroAdd(membroDto);
    }

    public BambinoDto bambinoAdd(BambinoDto bambinoDto){
        return bambinoFeignClient.addBambino(bambinoDto);
    }
}
