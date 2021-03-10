package com.maranata.budgetplanner.dao;


import com.maranata.budgetplanner.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long> {

    public List<Budget> findAllByCategoria(TipoCategoria categoria);

    @Query(value = "SELECT * FROM budgets WHERE flusso_id = :flussoId AND periodo = :periodo", nativeQuery = true)
    public List<Budget> findAllByFlussoAndPeriodo(Long flussoId, String periodo);


}
