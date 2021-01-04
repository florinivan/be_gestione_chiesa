package com.maranata.api.domain.dao;

import com.maranata.api.domain.entity.Presenza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresenzaRepository extends JpaRepository<Presenza,String> {
}
