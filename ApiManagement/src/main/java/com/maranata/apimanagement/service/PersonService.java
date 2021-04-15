package com.maranata.apimanagement.service;

import com.maranata.commonbean.management.entity.Person;
import com.maranata.commonbean.management.entity.Spouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maranata.apimanagement.feign.client.PersonFeignClient;

import java.util.Collection;

@Service
public class PersonService {

    @Autowired
    PersonFeignClient personFeignClient;

    public ResponseEntity<Collection<Person>> personList(){
        return personFeignClient.personList();
    }

    public ResponseEntity<Collection<Person>> findBypersonalNumber(String personalNumber) {
        return personFeignClient.findBypersonalNumber(personalNumber);
    }

    public ResponseEntity<Person> personAdd(Person person) {
        return personFeignClient.personAdd(person);
    }

    public ResponseEntity<Person> personUpdate(Person person, Long id) {
        return personFeignClient.personUpdate(person,id);
    }

    public ResponseEntity<Person> spouseAdd(Spouse spouse){
        return personFeignClient.personAdd((spouse).getPerson());
    }
}
