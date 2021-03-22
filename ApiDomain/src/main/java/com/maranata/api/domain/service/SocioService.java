package com.maranata.api.domain.service;

import com.maranata.api.domain.dao.SocioRepository;
import com.maranata.api.domain.entity.Socio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SocioService {

    @Autowired
    SocioRepository socioRepository;

    public ResponseEntity<Socio> updateSocio(Socio inSocio){
        try {
            Socio newSocio = socioRepository.findById(inSocio.getPersona().getId()).orElseThrow(RuntimeException::new);
            newSocio.setAnnotazioni(inSocio.getAnnotazioni());
            newSocio.setEmail(inSocio.getEmail());
            newSocio.setFlagCd(inSocio.getFlagCd());
            newSocio.setTelefono(inSocio.getTelefono());
            newSocio.setFlagStatuto(inSocio.getFlagStatuto());
            newSocio.setDataInscrizione(inSocio.getDataInscrizione());
            newSocio.setPersona(inSocio.getPersona());
            socioRepository.save(newSocio);
            return ResponseEntity.ok(newSocio);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
