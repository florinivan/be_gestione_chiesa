package com.maranata.budgetplanner.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.budgetplanner.entity.TipoTransazione;
import com.maranata.budgetplanner.entity.Transazione;
import com.maranata.budgetplanner.service.TransazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transazioni")
public class TransazioneController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    TransazioneService transazioneService;

    @GetMapping
    public ResponseEntity<List<Transazione>> findAll() {
        return new ResponseEntity<>(transazioneService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Transazione>> findByTipo(TipoTransazione tipo) {
        return new ResponseEntity<>(transazioneService.findBytipo(tipo), HttpStatus.OK);
    }

    @GetMapping("/findBy/{tipo}")
    public ResponseEntity<List<Transazione>> findTransactionsBytipoAndDateBetween(@PathVariable TipoTransazione tipo,
                                                                                  @RequestParam Long flussoId,
                                                                                  @RequestParam Long startDate,
                                                                                  @RequestParam Long endDate) {
        List<Transazione> transazioni = transazioneService.findTransactionsBytipoAndDateBetween( flussoId, tipo, startDate, endDate);
        return new ResponseEntity<>(transazioni, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Transazione>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(transazioneService.findbyId(id), HttpStatus.OK);
    }

    @PostMapping("/{tipo}")
    public ResponseEntity<Transazione> addTransazione(@RequestBody Transazione transazione, @PathVariable TipoTransazione tipo) {
        return new ResponseEntity<>(transazioneService.addTransazioneByTipo(tipo, transazione), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Transazione> updateTransazione(@PathVariable Long id, @RequestBody JsonPatch inTransazione) {
        try {
            Transazione transazione = transazioneService.findbyId(id).orElseThrow(RuntimeException::new);
            Transazione transazionePatched = applyPatchToCustomer(inTransazione, transazione);
            transazioneService.update(transazionePatched);
            return ResponseEntity.ok(transazionePatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping
    public void deleteById(@PathVariable Long id) {
        transazioneService.deleteById(id);
    }

    private Transazione applyPatchToCustomer(JsonPatch patch, Transazione targetTransazione) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetTransazione, JsonNode.class));
        return objectMapper.treeToValue(patched, Transazione.class);
    }
}


