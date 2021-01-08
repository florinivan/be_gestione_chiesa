package com.maranata.api.domain.dao;

import com.maranata.api.domain.entity.Bambino;
import com.maranata.api.domain.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BambinoRepository extends JpaRepository<Bambino,Long> {

    @Query("SELECT COUNT(b)>0 FROM Bambino b WHERE b.persona.codiceFiscale=:codiceFiscale")
    Boolean existBambinoByCodiceFiscale(String codiceFiscale);

    @Query("SELECT b FROM Bambino b WHERE b.codiceFiscalePadre =: codiceFiscalePadre")
    Bambino findBambinoByCfPadre(String codiceFiscalePadre);

    @Query("SELECT b FROM Bambino b WHERE b.codiceFiscaleMadre =: codiceFiscaleMadre")
    Bambino findBambinoByCfMadre(String codiceFiscaleMadre);

}
