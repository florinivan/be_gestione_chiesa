package com.maranata.api.domain.service;

import com.maranata.api.domain.dao.BambinoRepository;
import com.maranata.api.domain.dao.ResidenzaRepository;
import com.maranata.api.domain.entity.Bambino;
import com.maranata.api.domain.entity.Residenza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResidenzaService {

    @Autowired
    ResidenzaRepository residenzaRepository;

    public ResponseEntity<Residenza> updateResidenza(Residenza inResidenza){
        try {
            Residenza newResidenza = residenzaRepository.findById(inResidenza.getId()).orElseThrow(RuntimeException::new);
            newResidenza.setComune(inResidenza.getComune());
            newResidenza.setPaese(inResidenza.getPaese());
            newResidenza.setRegione(inResidenza.getRegione());
            newResidenza.setProvincia(inResidenza.getProvincia());
            newResidenza.setVia(inResidenza.getVia());
            residenzaRepository.save(newResidenza);
            return ResponseEntity.ok(newResidenza);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
