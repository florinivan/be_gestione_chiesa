package com.maranata.budgetplanner.service;

import com.maranata.budgetplanner.dao.TransazioneRepository;
import com.maranata.budgetplanner.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransazioneService {

    @Autowired
    TransazioneRepository transazioneRepository;
    @Autowired
    FlussoService flussoService;
    @Autowired
    CategoriaService categoriaService;


    public List<Transazione> findAll(){
        return transazioneRepository.findAll();
    }


    public List <Transazione> findTransactionsBytipoAndDateBetween(Long flussoId,TipoTransazione tipo,Long startDate,Long endDate){
        return transazioneRepository.findTransactionsBytipoAndDateBetween(flussoId,tipo.name(),startDate,endDate);
    }


    public Optional<Transazione> findbyId(Long id){
        return transazioneRepository.findById(id);
    }

    public List<Transazione> findBytipo(TipoTransazione tipo){
        return transazioneRepository.findBytipo(tipo);
    }

    public Transazione addTransazioneByTipo (TipoTransazione tipo, Transazione transazione){
        Optional<Categoria> categoria = categoriaService.findByDescrizione(transazione.getCategoria().getDescrizione());
        List<FlussoValidazione> flusso = flussoService.findByIdUtente(transazione.getFlusso().getIdUtente());
        if(categoria.isPresent()&& categoria.get().getTipoCategoria()== TipoCategoria.TRANSAZIONE||
                !flusso.isEmpty())
        {
            try {
                transazione.setTipo(tipo);
                transazioneRepository.save(transazione);
            }catch (Exception e){
                //TOOD add custom exception
                throw e;
            }
        }else {
            //TOOD add custom exception
            throw new RuntimeException();
        }
        return transazione;
    }


    public ResponseEntity<Transazione> update(Transazione inTransazione){
        try{
            Transazione newTransazione = transazioneRepository.findById(inTransazione.getId()).orElseThrow(RuntimeException::new);
            newTransazione.setData(inTransazione.getData());
            newTransazione.setDescrizione(inTransazione.getDescrizione());
            newTransazione.setCategoria(inTransazione.getCategoria());
            newTransazione.setTags(inTransazione.getTags());
            newTransazione.setImportoAttuale(inTransazione.getImportoAttuale());
            newTransazione.setImportoStimato(inTransazione.getImportoStimato());
            newTransazione.setTipo(inTransazione.getTipo());
           // newTransazione.setBudget(inTransazione.getBudget());
            transazioneRepository.save(newTransazione);
            return ResponseEntity.ok(newTransazione);
        }catch (Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    }

    public void  deleteById (Long id){
        transazioneRepository.deleteById(id);
    }

}
