package com.maranata.domainbudgetplanner.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maranata.commonbean.budgetplanner.entity.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    public Optional<Category> findByDescription (String description);
}
