package com.maranata.api.domain.dao;

import com.maranata.api.domain.entity.Residenza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndirizzoResidenzaRepository extends JpaRepository<Residenza,String> {
}
