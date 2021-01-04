package com.maranata.api.domain.dao;

import com.maranata.api.domain.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona,String> {

    List<Persona> findAllPersonaByNome(@Param("nome") String nome);

}
