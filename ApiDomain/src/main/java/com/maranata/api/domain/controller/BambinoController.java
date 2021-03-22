package com.maranata.api.domain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.api.domain.dao.BambinoRepository;
import com.maranata.api.domain.dao.PersonaRepository;
import com.maranata.api.domain.entity.Bambino;
import com.maranata.api.domain.service.BambinoService;
import com.maranata.api.domain.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bambini")
public class BambinoController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    BambinoRepository bambinoRepository;
    @Autowired
    PersonaService personaService;
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    BambinoService bambinoService;

    @GetMapping
    public ResponseEntity<List<Bambino>> findAll(){
        List<Bambino> bambino = bambinoRepository.findAll();
        return new ResponseEntity<>(bambino,HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkBambinoByCf(@RequestParam String codiceFiscale) {
        return new ResponseEntity<Boolean>( bambinoRepository.existBambinoBycodiceFiscale(codiceFiscale),HttpStatus.OK);
    }

    @GetMapping("/{codice_fiscale_padre}")
    public Bambino getBambinoByCfPadre(@PathVariable String codiceFiscalePadre){
       return bambinoRepository.findBambinoByCfPadre(codiceFiscalePadre);
    }

    @GetMapping("/{codice_fiscale_madre}")
    public Bambino getBambinoByCfMadre(@PathVariable String codiceFiscaleMadre) {
        return bambinoRepository.findBambinoByCfMadre(codiceFiscaleMadre);
    }

    @PostMapping("/add")
    public ResponseEntity<Bambino> addBambino(@RequestBody Bambino bambino) {
        return new ResponseEntity<>(personaService.addPersonaBambino(bambino),HttpStatus.OK);
    }

    @PatchMapping(path = "/{id_bambino}" )
    public ResponseEntity<Bambino> updateBambino(@PathVariable Long id, @RequestBody JsonPatch inBambino) {
        try {
            Bambino bambino = bambinoRepository.findById(id).orElseThrow(RuntimeException::new);
            Bambino bambinoPatched = applyPatchToCustomer(inBambino, bambino);
            bambinoService.updateBambino(bambinoPatched);
            return ResponseEntity.ok(bambinoPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus>deleteBambini(@PathVariable Long id,@PathVariable Bambino bambino){
        personaRepository.deleteById(bambino.getPersona().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Bambino applyPatchToCustomer(JsonPatch patch, Bambino targetBambino) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetBambino, JsonNode.class));
        return objectMapper.treeToValue(patched, Bambino.class);
    }

}
