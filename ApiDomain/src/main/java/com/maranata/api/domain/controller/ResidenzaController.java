package com.maranata.api.domain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.api.domain.dao.ResidenzaRepository;
import com.maranata.api.domain.entity.Persona;
import com.maranata.api.domain.entity.Residenza;
import com.maranata.api.domain.service.ResidenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/residenza")
public class ResidenzaController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    ResidenzaRepository residenzaRepository;
    @Autowired
    ResidenzaService residenzaService;

    @GetMapping
    public ResponseEntity<List<Residenza>>findAll(){
        List<Residenza> residenza = residenzaRepository.findAll();
        return  new ResponseEntity<>(residenza,HttpStatus.OK);
    }

    @GetMapping("/{id_residenza}")
    public ResponseEntity<Residenza> findByid(@PathVariable Long id){
        Residenza residenza = residenzaRepository.findById(id).get();
        return new ResponseEntity<>(residenza,HttpStatus.OK);
    }

    @PatchMapping(path = "/{id_residenza}" )
    public ResponseEntity<Residenza>updateResidenza(@PathVariable Long id, @RequestBody JsonPatch inResidenza){
        try {
            Residenza residenza = residenzaRepository.findById(id).orElseThrow(RuntimeException::new);
            Residenza residenzaPatched = applyPatchToCustomer(inResidenza, residenza);
            residenzaService.updateResidenza(residenzaPatched);
            return ResponseEntity.ok(residenzaPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Residenza>deleteById(@PathVariable Long id){
        residenzaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Residenza applyPatchToCustomer(JsonPatch patch, Residenza targetResidenza) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetResidenza, JsonNode.class));
        return objectMapper.treeToValue(patched, Residenza.class);
    }
}
