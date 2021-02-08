package com.maranata.api.domain.controller;

import com.maranata.api.domain.dao.BambinoRepository;
import com.maranata.api.domain.dao.PersonaRepository;
import com.maranata.api.domain.entity.Bambino;
import com.maranata.api.domain.entity.Membro;
import com.maranata.api.domain.entity.Persona;
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

    @Autowired
    BambinoRepository bambinoRepository;
    @Autowired
    PersonaService personaService;
    @Autowired
    PersonaRepository personaRepository;

    @GetMapping
    public ResponseEntity<List<Bambino>> findAll(){
        List<Bambino> bambino = bambinoRepository.findAll();
        return new ResponseEntity<>(bambino,HttpStatus.OK);
    }

    @GetMapping("/{codiceFiscale}")
    public ResponseEntity<Bambino> getBambinoByCf(@PathVariable String codiceFiscale){
      bambinoRepository.existBambinoByCodiceFiscale(codiceFiscale);
      return new ResponseEntity<>(HttpStatus.OK);
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

    @PutMapping("/{id_bambino}")
    public ResponseEntity<Bambino> updateBambino(@RequestBody Bambino bambino,@PathVariable Long id) {
        Optional<Bambino> newBambino = bambinoRepository.findById(id);
        if (newBambino.isPresent()) {
            Bambino _bambino = newBambino.get();
            _bambino.setCodiceFiscaleMadre(bambino.getCodiceFiscaleMadre());
            _bambino.setCodiceFiscalePadre(bambino.getCodiceFiscalePadre());
            return new ResponseEntity<>(bambinoRepository.save(_bambino), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus>deleteBambini(@PathVariable Long id,@PathVariable Bambino bambino){
        personaRepository.deleteById(bambino.getPersona().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
