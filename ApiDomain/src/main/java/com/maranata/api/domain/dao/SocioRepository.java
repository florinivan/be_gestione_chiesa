package com.maranata.api.domain.dao;

import com.maranata.api.domain.entity.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<Socio,Long> {
}
