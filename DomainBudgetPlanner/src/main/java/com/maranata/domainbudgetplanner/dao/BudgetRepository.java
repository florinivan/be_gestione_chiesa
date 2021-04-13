package com.maranata.domainbudgetplanner.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.maranata.commonbean.budgetplanner.entity.*;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long> {

    @Query(value= "SELECT *  FROM budgets b LEFT JOIN category c ON b.category_id = c.id WHERE b.category_id = :categoryId",
            countQuery = "SELECT COUNT(*) FROM budgets",
            nativeQuery = true)
    public List<Budget> findBycategory(Long categoryId);

    @Query(value = "SELECT * FROM budgets WHERE validation_id = :validationId" +
            " AND end_date BETWEEN :startDate" +
            " AND :endDate", nativeQuery = true)
    public List<Budget> findAllByValidationAndPeriod(Long validationId, Long startDate, Long endDate);

    @Query(value = "SELECT * FROM budgets WHERE end_date BETWEEN :startDate AND :endDate",nativeQuery = true)
    public List<Budget> findByDate(Long startDate, Long endDate);

}
