package com.maranata.domainbudgetplanner.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import com.maranata.commonbean.budgetplanner.entity.ValidationType;

@Controller
public interface ValidationTypeRepository extends JpaRepository<ValidationType,Long> {
}
