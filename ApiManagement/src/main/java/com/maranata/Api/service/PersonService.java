package com.maranata.Api.service;

import com.maranata.Api.dto.SpouseDto;
import com.maranata.Api.dto.PersonDto;
import com.maranata.Api.feign.client.PersonFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PersonService {

    @Autowired
    PersonFeignClient personFeignClient;

    public ResponseEntity<Collection<PersonDto>> personList(){
        return personFeignClient.personList();
    }

    public ResponseEntity<Collection<PersonDto>> findBypersonalNumber(String personalNumber) {
        return personFeignClient.findBypersonalNumber(personalNumber);
    }

    public ResponseEntity<PersonDto> personAdd(PersonDto personDto) {
        return personFeignClient.personAdd(personDto);
    }

    public ResponseEntity<PersonDto> personUpdate(PersonDto personDto, Long id) {
        return personFeignClient.personUpdate(personDto,id);
    }

    public ResponseEntity<PersonDto> spouseAdd(SpouseDto spouseDto){
        return personFeignClient.personAdd((spouseDto).getPerson());
    }
}
