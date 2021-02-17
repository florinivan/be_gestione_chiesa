package com.maranata.api.domain.controller;

import com.maranata.api.domain.dao.MembroRepository;
import com.maranata.api.domain.entity.Membro;
import com.maranata.api.domain.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membri")
public class MembroController {

    @Autowired
    MembroRepository membroRepository;
    @Autowired
    PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<Membro>> findAll(){
        List<Membro> membro = membroRepository.findAll();
        return new ResponseEntity<>(membro,HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkMembroByCf(@RequestParam String codiceFiscale) {
        return new ResponseEntity<Boolean>( membroRepository.existMembroBycodiceFiscale(codiceFiscale),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Membro> addPersonaMembro( @RequestBody Membro membro) {
        return new ResponseEntity<>(personaService.addPersonaMembro(membro),HttpStatus.OK);
    }

    @PutMapping("/{id_membro}")
    public ResponseEntity<Membro> updateMembro(@RequestBody Membro membro,@PathVariable Long id){
            membroRepository.findById(id);
            membro.setAttivoPassivo(membro.getAttivoPassivo());
            membro.setCfConiuge(membro.getCfConiuge());
            membro.setCoroLode(membro.getCoroLode());
            membro.setCenaDelSignore(membro.getCenaDelSignore());
            membro.setEmail(membro.getEmail());
            membro.setDurataDisciplina(membro.getDurataDisciplina());
            membro.setP_scuola_domenicale(membro.getP_scuola_domenicale());
            membro.setPreghiera(membro.getPreghiera());
            membro.setSottodisciplina(membro.getSottodisciplina());
            membro.setTelefono(membro.getTelefono());
            return new ResponseEntity<>(membroRepository.save(membro),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus>deleteMembri(){
        membroRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}