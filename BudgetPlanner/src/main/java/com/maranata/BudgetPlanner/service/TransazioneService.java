package com.maranata.BudgetPlanner.service;

import com.maranata.BudgetPlanner.dao.CategoriaRepository;
import com.maranata.BudgetPlanner.dao.TransazioneRepository;
import com.maranata.BudgetPlanner.entity.Transazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransazioneService {

    Transazione transazione;

    @Autowired
    TransazioneRepository transazioneRepository;
    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Transazione> findAll(){
        return transazioneRepository.findAll();
    }

    public Optional<Transazione> findbyId(Long id){
        return transazioneRepository.findById(id);
    }

    @Transactional
    public Transazione add(Transazione transazione){
       categoriaRepository.save(transazione.getCategoria());
       return transazioneRepository.save(transazione);
    }

    public ResponseEntity<Transazione> update(Transazione inTransazione){
        try{
            Transazione newTransazione = transazioneRepository.findById(inTransazione.getId()).orElseThrow(RuntimeException::new);
            newTransazione.setData(inTransazione.getData());
            newTransazione.setDescrizione(inTransazione.getDescrizione());
            newTransazione.setSomma(inTransazione.getSomma());
            newTransazione.setCategoria(inTransazione.getCategoria());
            transazioneRepository.save(newTransazione);
            return ResponseEntity.ok(newTransazione);
        }catch (Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    }

}
