package com.maranata.api.domain.service;

import com.maranata.api.domain.dao.BambinoRepository;
import com.maranata.api.domain.dao.MembroRepository;
import com.maranata.api.domain.dao.PersonaRepository;
import com.maranata.api.domain.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    //TODO  implement findAll method and insert method

    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    MembroRepository membroRepository;
    @Autowired
    BambinoRepository bambinoRepository;

    public List<Persona> getAllPersona(){
        return personaRepository.findAll();
    }

    public List<Persona> getAllPersoneByName(String nome) {
        return personaRepository.findAllPersonaByNome(nome);
    }

    /**
     * get all membri and bambini from persona using query repository method
     * @return
     */
    public List<Persona> getAllMembriBambiniByQuery(){
        return  personaRepository.findAllMembriBambini();
    }

    /**
     * get all membri and bambini from persona using entity repository
     * @return
     */
    public List<Persona> getAllMembriBambiniByRepository(){
        return  personaRepository.findAll().stream().filter(persona->membroRepository.existMembroByCodiceFiscale(persona.getCodiceFiscale()))
                .filter(persona->bambinoRepository.existBambinoByCodiceFiscale(persona.getCodiceFiscale())).collect(Collectors.toList());
    }

    public Persona insertPersona(Persona persona){
        return personaRepository.save(persona);
    }


}
