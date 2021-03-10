package com.maranata.budgetplanner.dao;

import com.maranata.budgetplanner.entity.StatoValidazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface StatoRepository extends JpaRepository<StatoValidazione,Long> {
}
