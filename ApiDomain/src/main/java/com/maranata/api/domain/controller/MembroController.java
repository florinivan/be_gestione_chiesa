package com.maranata.api.domain.controller;

import com.maranata.api.domain.dao.MembroRepository;
import com.maranata.api.domain.entity.Membro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/domain/membri")
public class MembroController {

//TODO implement all CRUD Rest Services : findAll, insert, findAllById, delete using update repository method,update

    @Autowired
    MembroRepository membroRepository;


    @GetMapping("/membri")
    public  List<Membro> getAllMembri(){
        return membroRepository.findAll();
    }

    @GetMapping("/membri/{id_membro}")
    public  Membro getMembroById(@PathVariable Long id){
        return membroRepository.findById(id).get();
    }

    @GetMapping("/membri/{persona}")
    public Boolean existMembroByCodiceFiscale(@PathVariable String codiceFiscale){
        return membroRepository.existMembroByCodiceFiscale(codiceFiscale);
    }

    @PostMapping("/membri")
    public Membro insertMembro(@RequestBody Membro nuovoMembro){
        return membroRepository.save(nuovoMembro);
    }

    @PutMapping("/membri/{id_membro}")
    public Membro saveAndUpdateMembro(@RequestBody Membro nuovoMembro,@PathVariable Long id){
        return membroRepository.findById(id).map(membro -> {
            membro.setAttivoPassivo(nuovoMembro.getAttivoPassivo());
            membro.setCfConiuge(nuovoMembro.getCfConiuge());
            membro.setCoroLode(nuovoMembro.getCoroLode());
            membro.setCenaDelSignore(nuovoMembro.getCenaDelSignore());
            membro.setEmail(nuovoMembro.getEmail());
            membro.setDurataDisciplina(nuovoMembro.getDurataDisciplina());
            membro.setP_scuola_domenicale(nuovoMembro.getP_scuola_domenicale());
            membro.setPreghiera(nuovoMembro.getPreghiera());
            membro.setSottodisciplina(nuovoMembro.getSottodisciplina());
            membro.setTelefono(nuovoMembro.getTelefono());
            return membroRepository.save(membro);
        }).orElseGet(() -> {
            nuovoMembro.setIdMembro(id);
            return membroRepository.save(nuovoMembro);
        });

    }

}