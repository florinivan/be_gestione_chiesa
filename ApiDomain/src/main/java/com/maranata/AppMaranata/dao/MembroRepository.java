package com.maranata.AppMaranata.dao;

import com.maranata.AppMaranata.entity.Membro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembroRepository extends JpaRepository<Membro, String> {

}
