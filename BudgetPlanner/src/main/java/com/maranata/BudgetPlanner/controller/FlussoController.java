package com.maranata.budgetplanner.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.budgetplanner.entity.Budget;
import com.maranata.budgetplanner.entity.FlussoValidazione;
import com.maranata.budgetplanner.entity.StatoValidazione;
import com.maranata.budgetplanner.service.FlussoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flussi")
public class FlussoController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    FlussoService flussoService;

    @GetMapping
    public ResponseEntity<List<FlussoValidazione>> findAll(){
        return new ResponseEntity<>(flussoService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<FlussoValidazione>add(@RequestBody FlussoValidazione flusso){
        return new ResponseEntity<>(flussoService.add(flusso),HttpStatus.CREATED);
    }

   @PatchMapping
    public ResponseEntity<FlussoValidazione> update(@PathVariable Long id, @RequestBody JsonPatch inFlusso) {
        try {
            FlussoValidazione flusso = flussoService.findById(id).orElseThrow(RuntimeException::new);
            FlussoValidazione flussoPatched = applyPatchToFlusso(inFlusso, flusso);
            flussoService.update(flussoPatched);
            return ResponseEntity.ok(flussoPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    private FlussoValidazione applyPatchToFlusso(JsonPatch patch, FlussoValidazione targetFlusso) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetFlusso, JsonNode.class));
        return objectMapper.treeToValue(patched, FlussoValidazione.class);
    }

}
