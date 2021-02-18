package com.maranata.BudgetPlanner.dao;

import com.maranata.BudgetPlanner.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
