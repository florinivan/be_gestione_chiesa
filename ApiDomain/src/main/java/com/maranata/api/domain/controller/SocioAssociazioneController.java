package com.maranata.api.domain.controller;

import com.maranata.api.domain.dao.SocioAssociazioneRepository;
import com.maranata.api.domain.entity.SocioAssociazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domain/soci_associazione")
public class SocioAssociazioneController {

    @Autowired
    SocioAssociazioneRepository socioRepository;

    @GetMapping("/soci_associazione")
    public List<SocioAssociazione> getAllSociAssociazione(){
        return socioRepository.findAll();
    }

    @GetMapping("/soci_associazione/{id_socio}")
    public SocioAssociazione getSociById(@PathVariable Long id){
        return socioRepository.findById(id).get();
    }

    @PostMapping("/soci_associazione")
    public SocioAssociazione createSocio(@RequestBody SocioAssociazione nuovoSocio){
        return socioRepository.save(nuovoSocio);
    }

    @PutMapping("/socio_associazione/{id_socio}")
    public SocioAssociazione saveAndUpdateSocio(@RequestBody SocioAssociazione nuovoSocio,@PathVariable Long id){
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
