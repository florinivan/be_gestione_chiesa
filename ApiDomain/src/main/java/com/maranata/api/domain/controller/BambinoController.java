package com.maranata.api.domain.controller;

import com.maranata.api.domain.dao.BambinoRepository;
import com.maranata.api.domain.entity.Bambino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/domain/bambini")
public class BambinoController {

    @Autowired
    BambinoRepository bambinoRepository;

    @GetMapping("/bambini")
    public List<Bambino> getAllBambini(){
        return bambinoRepository.findAll();
    }

    @GetMapping("/bambini/{id_bambino}")
    public Bambino getBambinoById(@PathVariable Long id){
        return bambinoRepository.findById(id).get();
    }

    @GetMapping("/bambini/{codice_fiscale}")
    public Boolean existBAmbinoByCodiceFiscale(@PathVariable String codiceFiscale){
        return bambinoRepository.existBambinoByCodiceFiscale(codiceFiscale);
    }

    @GetMapping("/bambini/{codice_fiscale_padre}")
    public Bambino getBambinoByCfPadre(@PathVariable String codiceFiscalePadre){
       return bambinoRepository.findBambinoByCfPadre(codiceFiscalePadre);
    }

    @GetMapping("/bambini/{codice_fiscale_madre}")
    public Bambino getBambinoByCfMadre(@PathVariable String codiceFiscaleMadre) {
        return bambinoRepository.findBambinoByCfMadre(codiceFiscaleMadre);
    }

    @PostMapping("/bambini")
    public Bambino insertBambino(@RequestBody Bambino nuovoBambino){
        return bambinoRepository.save(nuovoBambino);
    }

    @PutMapping("/bambini/{id_bambino}")
    public Bambino saveAndUpdateBambino(@RequestBody Bambino nuovoBambino,@PathVariable Long id){
        return bambinoRepository.findById(id).map(bambino -> {
            bambino.setCodiceFiscaleMadre(bambino.getCodiceFiscaleMadre());
            bambino.setCodiceFiscalePadre(bambino.getCodiceFiscalePadre());
            return bambinoRepository.save(bambino);
        }).orElseGet(()->{
            nuovoBambino.setIdBambino(id);
            return bambinoRepository.save(nuovoBambino);
        });
    }
}
//TODO implements findAll, findByIdGenitore,insert, update