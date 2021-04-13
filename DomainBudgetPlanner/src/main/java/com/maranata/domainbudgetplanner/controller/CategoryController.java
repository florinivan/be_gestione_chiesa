package com.maranata.domainbudgetplanner.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.commonbean.budgetplanner.entity.Category;
import com.maranata.domainbudgetplanner.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    CategoryService categoryService;


    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> findById(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.findById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> add (@RequestBody Category category){
        return new ResponseEntity<>(categoryService.add(category),HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Category>update (@PathVariable Long id, @RequestBody JsonPatch inCategoria){
        try{
            Category category = categoryService.findById(id).orElseThrow(RuntimeException::new);
            Category categoryPatched = applyPatchToCategoria(inCategoria, category);
            categoryService.update(categoryPatched);
            return ResponseEntity.ok(categoryPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping
    public void deleteById(@PathVariable Long id){
        categoryService.deletebyId(id);
    }

    private Category applyPatchToCategoria(JsonPatch patch, Category targetCategory) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetCategory, JsonNode.class));
        return objectMapper.treeToValue(patched, Category.class);
    }
}
