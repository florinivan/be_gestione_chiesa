package com.maranata.BudgetPlanner.dao;

import com.maranata.BudgetPlanner.entity.Transazione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransazioneRepository extends JpaRepository<Transazione,Long> {
}
