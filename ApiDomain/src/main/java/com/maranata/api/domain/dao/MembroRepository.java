package com.maranata.api.domain.dao;

import com.maranata.api.domain.entity.Bambino;
import com.maranata.api.domain.entity.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MembroRepository extends JpaRepository<Membro, Long> {

    @Query("SELECT COUNT(m)>0 FROM Membro m WHERE m.persona.codiceFiscale =:codiceFiscale")
    Boolean existMembroByCodiceFiscale(String codiceFiscale);
}



