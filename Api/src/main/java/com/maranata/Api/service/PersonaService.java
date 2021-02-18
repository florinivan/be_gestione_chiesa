package com.maranata.Api.service;

import com.maranata.Api.dto.ConiugeDto;
import com.maranata.Api.dto.PersonaDto;
import com.maranata.Api.feign.client.PersonaFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PersonaService {

    @Autowired
    PersonaFeignClient personaFeignClient;

    public ResponseEntity<Collection<PersonaDto>>personaList(){
        return personaFeignClient.personaList();
    }


    public ResponseEntity<Collection<PersonaDto>> findBycodiceFiscale(String codiceFiscale) {
        return personaFeignClient.findBycodiceFiscale(codiceFiscale);
    }

    public PersonaDto personaAdd(PersonaDto personaDto) {
        return personaFeignClient.personaAdd(personaDto);
    }


    public PersonaDto personaUpdate(PersonaDto personaDto,Long id) {
        return personaFeignClient.personaUpdate(personaDto,id);
    }

    public PersonaDto coniugeAdd(ConiugeDto coniugeDto){
        return personaFeignClient.personaAdd((coniugeDto).getPersona());
    }
}
