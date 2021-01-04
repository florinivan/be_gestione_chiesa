package com.maranata.api.domain.dao;

import com.maranata.api.domain.entity.Bambino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BambinoRepository extends JpaRepository<Bambino,String> {
}
