package com.maranata.AppMaranata.service;

import com.maranata.AppMaranata.dao.PersonaRepository;
import com.maranata.AppMaranata.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    public List<Persona> getAllPersoneByName(String name) {
        return personaRepository.findAllPersonaByNome(name);
    }
}
