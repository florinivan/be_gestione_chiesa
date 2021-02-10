package com.maranata.api.domain.controller;

import com.maranata.api.domain.dao.SocioRepository;
import com.maranata.api.domain.entity.Membro;
import com.maranata.api.domain.entity.Socio;
import com.maranata.api.domain.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soci")
public class SocioController {

    @Autowired
    SocioRepository socioRepository;
    @Autowired
    PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<Socio>> findAll(){
        List<Socio> socio = socioRepository.findAll();
        return new ResponseEntity<>(socio,HttpStatus.OK);
    }

    @GetMapping("/{id_socio}")
    public ResponseEntity<Socio> findById(@PathVariable Long id){
        socioRepository.findById(id).get();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Socio> addSocio(@RequestBody Socio socio) {
        return new ResponseEntity<>(personaService.addPersonaSocio(socio), HttpStatus.OK);
    }

    @PutMapping("/{id_socio}")
    public Socio saveAndUpdateSocio(@RequestBody Socio nuovoSocio, @PathVariable Long id){
        return socioRepository.findById(id).map(socioAssociazione -> {
            socioAssociazione.setAnnotazioni(socioAssociazione.getAnnotazioni());
            socioAssociazione.setEmail(socioAssociazione.getEmail());
            socioAssociazione.setTelefono(socioAssociazione.getTelefono());
            socioAssociazione.setFlagCd(socioAssociazione.getFlagCd());
            socioAssociazione.setFlagStatuto(socioAssociazione.getFlagStatuto());
            return socioRepository.save(socioAssociazione);
        }).orElseGet(()->{
            nuovoSocio.setIdSocio(id);
            return socioRepository.save(nuovoSocio);
        });
    }
}
