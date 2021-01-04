package com.maranata.api.domain.service;

import com.maranata.api.domain.dao.PersonaRepository;
import com.maranata.api.domain.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    public List<Persona> getAllPersoneByName(String name) {
        return personaRepository.findAllPersonaByNome(name);
    }
}
