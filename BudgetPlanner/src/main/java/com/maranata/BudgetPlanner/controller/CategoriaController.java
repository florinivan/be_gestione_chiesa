package com.maranata.budgetplanner.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.budgetplanner.entity.Categoria;
import com.maranata.budgetplanner.entity.TipoCategoria;
import com.maranata.budgetplanner.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorie")
public class CategoriaController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    CategoriaService categoriaService;


    @GetMapping
    public ResponseEntity<List<Categoria>> findAll(){
        return new ResponseEntity<>(categoriaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Categoria>> findById(@PathVariable Long id){
        return new ResponseEntity<>(categoriaService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/{tipo}")
    public ResponseEntity<Categoria> add (@RequestBody Categoria categoria, @PathVariable TipoCategoria tipo){
        return new ResponseEntity<>(categoriaService.add(categoria,tipo),HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Categoria>update (@PathVariable Long id, @RequestBody JsonPatch inCategoria){
        try{
            Categoria categoria = categoriaService.findById(id).orElseThrow(RuntimeException::new);
            Categoria categoriaPatched = applyPatchToCategoria(inCategoria, categoria);
            categoriaService.update(categoriaPatched);
            return ResponseEntity.ok(categoriaPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping
    public void deleteById(@PathVariable Long id){
        categoriaService.deletebyId(id);
    }

    private Categoria applyPatchToCategoria(JsonPatch patch, Categoria targetCategoria) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetCategoria, JsonNode.class));
        return objectMapper.treeToValue(patched, Categoria.class);
    }
}
