package com.maranata.budgetplanner.service;

import com.maranata.budgetplanner.dao.FlussoRepository;
import com.maranata.budgetplanner.dao.StatoRepository;
import com.maranata.budgetplanner.entity.Budget;
import com.maranata.budgetplanner.entity.FlussoValidazione;
import com.maranata.budgetplanner.entity.StatoValidazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlussoService {

    @Autowired
    FlussoRepository flussoRepository;
    @Autowired
    StatoRepository statoRepository;

    public List<FlussoValidazione> findAll(){
        return flussoRepository.findAll();
    }

    public Optional<FlussoValidazione> findById(Long id){
        return flussoRepository.findById(id);
    }

    public List<FlussoValidazione> findByIdUtente(Long idUtente){
        return flussoRepository.findByidUtente(idUtente);
    }

    public FlussoValidazione add(FlussoValidazione flusso){
        statoRepository.save(flusso.getStato());
        return flussoRepository.save(flusso);
    }

    public ResponseEntity<FlussoValidazione> update(FlussoValidazione inFlusso){
        try{
            FlussoValidazione newFlusso = flussoRepository.findById(inFlusso.getId()).orElseThrow(RuntimeException::new);
            newFlusso.setIdUtente(inFlusso.getId());
            newFlusso.setStato(inFlusso.getStato());
            flussoRepository.save(newFlusso);
            return ResponseEntity.ok(newFlusso);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

     public void deleteById(Long id){
         flussoRepository.deleteById(id);
     }
}
