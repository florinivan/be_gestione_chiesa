package com.maranata.api.domain.service;

import com.maranata.api.domain.dao.BambinoRepository;
import com.maranata.api.domain.dao.MembroRepository;
import com.maranata.api.domain.entity.Bambino;
import com.maranata.api.domain.entity.Membro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BambinoService {

    @Autowired
    BambinoRepository bambinoRepository;

    public ResponseEntity<Bambino> updateBambino(Bambino inBambino){
        try {
            Bambino newBambino = bambinoRepository.findById(inBambino.getPersona().getId()).orElseThrow(RuntimeException::new);
            newBambino.setPersona(inBambino.getPersona());
            newBambino.setCodiceFiscalePadre(inBambino.getCodiceFiscalePadre());
            newBambino.setCodiceFiscaleMadre(inBambino.getCodiceFiscaleMadre());
            bambinoRepository.save(newBambino);
            return ResponseEntity.ok(newBambino);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
