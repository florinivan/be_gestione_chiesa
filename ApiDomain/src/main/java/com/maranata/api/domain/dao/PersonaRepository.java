package com.maranata.api.domain.dao;

import com.maranata.api.domain.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona,String> {

    List<Persona> findAllPersonaByNome(String nome);

    @Query("SELECT p FROM Persona p WHERE EXISTS ( SELECT m FROM Membro m WHERE p.id = m.persona.id ) OR EXISTS (SELECT b FROM Bambino b WHERE p.id = b.persona.id)")
    List<Persona> findAllMembriBambini();


}
