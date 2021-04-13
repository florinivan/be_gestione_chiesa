package com.maranata.budgetplanner.dao;

import com.maranata.budgetplanner.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    public Optional<Category> findByDescription (String description);
}
