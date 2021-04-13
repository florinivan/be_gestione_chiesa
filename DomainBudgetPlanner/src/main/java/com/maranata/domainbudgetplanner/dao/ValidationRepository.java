package com.maranata.domainbudgetplanner.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maranata.commonbean.budgetplanner.entity.Validation;

import java.util.List;

public interface ValidationRepository extends JpaRepository<Validation,Long> {

    List<Validation> findByUserId(Long userId);
}

