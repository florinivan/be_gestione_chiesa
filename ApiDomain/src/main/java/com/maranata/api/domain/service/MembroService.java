package com.maranata.api.domain.service;

import com.maranata.api.domain.dao.MembroRepository;
import com.maranata.api.domain.entity.Membro;
import com.maranata.api.domain.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MembroService {

    @Autowired
    MembroRepository membroRepository;

    public ResponseEntity<Membro> updateMembro(Membro inMembro){
        try {
            Membro newMembro = membroRepository.findById(inMembro.getPersona().getId()).orElseThrow(RuntimeException::new);
            newMembro.setIdMembro(inMembro.getIdMembro());
            newMembro.setTelefono(inMembro.getTelefono());
            newMembro.setSottodisciplina(inMembro.getSottodisciplina());
            newMembro.setPreghiera(inMembro.getPreghiera());
            newMembro.setEmail(inMembro.getEmail());
            newMembro.setDurataDisciplina(inMembro.getDurataDisciplina());
            newMembro.setCenaDelSignore(inMembro.getCenaDelSignore());
            newMembro.setCoroLode(inMembro.getCoroLode());
            newMembro.setCfConiuge(inMembro.getCfConiuge());
            newMembro.setTrasferito(inMembro.getTrasferito());
            membroRepository.save(newMembro);
            return ResponseEntity.ok(newMembro);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
