package com.maranata.budgetplanner.dao;

import com.maranata.budgetplanner.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    public Optional<Categoria> findByDescrizione (String descrizione);
}
