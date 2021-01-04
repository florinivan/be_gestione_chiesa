package com.maranata.AppMaranata.dao;

import com.maranata.AppMaranata.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona,String> {

    List<Persona> findAllPersonaByNome(@Param("nome") String nome);

}
