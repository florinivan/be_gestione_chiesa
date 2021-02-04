package com.maranata.api.domain.service;

import com.maranata.api.domain.dao.BambinoRepository;
import com.maranata.api.domain.dao.MembroRepository;
import com.maranata.api.domain.dao.PersonaRepository;
import com.maranata.api.domain.entity.Bambino;
import com.maranata.api.domain.entity.Membro;
import com.maranata.api.domain.entity.Persona;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
    @Autowired
    ResidenzaService residenzaService;

    public List<Persona> findAll(){
        return personaRepository.findAll();
    }

    public Optional<Persona> findAllById(long id){
        return personaRepository.findById(id);
    }

    public Persona findPersonaByCodiceFiscale(String codiceFiscale) {
        return personaRepository.findPersonaByCodiceFiscale(codiceFiscale);
    }

    /**
     * get all membri and bambini from persona using query repository method
     * @return
     */
    public List<Persona> findAllMembriBambiniByQuery(){
        return  personaRepository.findAllMembriBambini();
    }

    /**
     * get all membri and bambini from persona using entity repository
     * @return
     */
    public List<Persona> findAllMembriBambiniByRepository(){
        return  personaRepository.findAll().stream().filter(persona->membroRepository.existMembroByCodiceFiscale(persona.getCodiceFiscale()))
                .filter(persona->bambinoRepository.existBambinoByCodiceFiscale(persona.getCodiceFiscale())).collect(Collectors.toList());
    }

    public Persona addPersona(Persona persona){
        residenzaService.add(persona.getResidenza());
        return personaRepository.save(persona);
    }

    @Transactional
    public Membro addPersonaMembro (Membro membro){
      addPersona(membro.getPersona());
      return membroRepository.save(membro);
    }

    @Transactional
    public Bambino addPersonaBambino (Bambino bambino){
        addPersona(bambino.getPersona());
        return bambinoRepository.save(bambino);
    }

    public Persona updatePersona(Long id,Persona newPersona){
        return personaRepository.findById(id).map(persona -> {
            persona.setNome(newPersona.getNome());
            persona.setCognome(newPersona.getCognome());
            return personaRepository.save(persona);
        }).orElseGet(() -> {
            return null;
        });
    }

    public void deleteAllPersone(){
        personaRepository.deleteAll();
    }

    public Persona deleteByCodiceFiscale(String codiceFiscale){
       return personaRepository.deleteByCodiceFiscale(codiceFiscale);
    }

}
