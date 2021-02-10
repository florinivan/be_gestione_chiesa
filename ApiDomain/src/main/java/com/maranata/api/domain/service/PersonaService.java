package com.maranata.api.domain.service;

import com.maranata.api.domain.dao.*;
import com.maranata.api.domain.entity.Bambino;
import com.maranata.api.domain.entity.Membro;
import com.maranata.api.domain.entity.Persona;
import com.maranata.api.domain.entity.Socio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    MembroRepository membroRepository;
    @Autowired
    BambinoRepository bambinoRepository;
    @Autowired
    SocioRepository socioRepository;
    @Autowired
    ResidenzaRepository residenzaRepository;



    public List<Persona> findAll(){
        return personaRepository.findAll();
    }

    public Optional<Persona> findById(long id){
        return personaRepository.findById(id);
    }

    public List<Persona> findPersonaByCf(String codiceFiscale) {
        return personaRepository.findPersonaBycodiceFiscale(codiceFiscale);
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
        return  personaRepository.findAll().stream().filter(persona->membroRepository.existMembroBycodiceFiscale(persona.getCodiceFiscale()))
                .filter(persona->bambinoRepository.existBambinoBycodiceFiscale(persona.getCodiceFiscale())).collect(Collectors.toList());
    }

    @Transactional
    public Persona addPersona(Persona persona){
        residenzaRepository.save(persona.getResidenza());
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

    @Transactional
    public Socio addPersonaSocio (Socio socio){
        addPersona(socio.getPersona());
        return socioRepository.save(socio);
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

    public void deletePersona(Long idPersona){
        Persona persona= personaRepository.findById(idPersona).get();
        residenzaRepository.deleteById(persona.getResidenza().getId());
        personaRepository.deleteById(idPersona);
    }
}
