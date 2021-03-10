package com.maranata.budgetplanner.dao;

import com.maranata.budgetplanner.entity.TipoTransazione;
import com.maranata.budgetplanner.entity.Transazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TransazioneRepository extends JpaRepository<Transazione, Long> {

    public List<Transazione> findBytipo(TipoTransazione tipo);

    @Query(value =
            "SELECT * \n" +
                    "FROM transazioni t\n   WHERE t.flusso_id = :flussoId " +
                    "AND LOWER(t.tipo) = LOWER(:tipo)" +
                    "AND t.data BETWEEN :startDate " +
                    "AND :endDate", nativeQuery = true)
    public List<Transazione> findTransactionsBytipoAndDateBetween(Long flussoId,
                                                                  String tipo,
                                                                  Long startDate,
                                                                  Long endDate);


}
