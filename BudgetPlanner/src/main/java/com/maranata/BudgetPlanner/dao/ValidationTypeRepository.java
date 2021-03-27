package com.maranata.budgetplanner.dao;

import com.maranata.budgetplanner.entity.ValidationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface ValidationTypeRepository extends JpaRepository<ValidationType,Long> {
}
