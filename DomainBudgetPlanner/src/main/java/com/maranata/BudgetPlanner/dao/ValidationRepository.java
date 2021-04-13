package com.maranata.budgetplanner.dao;

import com.maranata.budgetplanner.entity.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValidationRepository extends JpaRepository<Validation,Long> {

    List<Validation> findByUserId(Long userId);
}

