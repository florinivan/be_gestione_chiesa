package com.maranata.budgetplanner.dao;

import com.maranata.budgetplanner.entity.FlussoValidazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlussoRepository extends JpaRepository<FlussoValidazione,Long> {

    List<FlussoValidazione> findByidUtente(Long idUtente);
}

