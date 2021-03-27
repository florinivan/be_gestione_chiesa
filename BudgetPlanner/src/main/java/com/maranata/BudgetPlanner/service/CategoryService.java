package com.maranata.budgetplanner.service;

import com.maranata.budgetplanner.dao.CategoryRepository;
import com.maranata.budgetplanner.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

    public Optional<Category> findByDescription(String description){
        return categoryRepository.findByDescription(description);
    }

    public Category add (Category category){
        return categoryRepository.save(category);
    }

    public ResponseEntity<Category> update(Category inCategory){
        try{
            Category newCategory = categoryRepository.findById(inCategory.getId()).orElseThrow(RuntimeException::new);
            newCategory.setDescription(inCategory.getDescription());
            categoryRepository.save(newCategory);
            return ResponseEntity.ok(newCategory);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public void deletebyId(Long id){
        categoryRepository.deleteById(id);

    }
}
