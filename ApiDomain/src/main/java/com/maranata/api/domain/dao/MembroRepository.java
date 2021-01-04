package com.maranata.api.domain.dao;

import com.maranata.api.domain.entity.Membro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembroRepository extends JpaRepository<Membro, String> {

}
