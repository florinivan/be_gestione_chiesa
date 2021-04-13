package com.maranata.domainbudgetplanner.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.maranata.commonbean.budgetplanner.entity.Budget;
import com.maranata.commonbean.budgetplanner.entity.Transaction;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    @Query(value =
            "SELECT * \n" +
                    "FROM transactions t\n " +
                    "LEFT JOIN categories c\n" +
                    "ON t.category_id = c.id\n" +
                    "WHERE t.validation_id = :validationId " +
                    "AND LOWER(c.type) = LOWER(:type) " +
                    "AND t.data BETWEEN :startDate " +
                    "AND :endDate", nativeQuery = true)
    public List<Transaction> findTransactionsBytypeAndDateBetween(Long validationId,
                                                                  String type,
                                                                  Long startDate,
                                                                  Long endDate);

    @Query(value = "SELECT * FROM transactions WHERE date BETWEEN :startDate" +
            " AND :endDate", nativeQuery = true)
    public List<Transaction> findByPeriod(Long startDate, Long endDate);
}
