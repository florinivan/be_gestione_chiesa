package com.maranata.budgetplanner.service;

import com.maranata.budgetplanner.dao.BudgetRepository;
import com.maranata.budgetplanner.dao.FlussoRepository;
import com.maranata.budgetplanner.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    @Autowired
    CategoriaService categoriaService;
    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    FlussoService flussoService;

    public List<Budget> findAll(){
        return budgetRepository.findAll();
    }

    public Optional<Budget> findById(Long id){
        return budgetRepository.findById(id);
    }

    public List<Budget> findAllByFlussoAndPeriodo(Long flussoId, String  periodo){
        return budgetRepository.findAllByFlussoAndPeriodo(flussoId,periodo);
    }

    @Transactional
    public Budget addBudgetByCategory (Budget budget){
        Optional<Categoria> categoria = categoriaService.findByDescrizione(budget.getCategoria().getDescrizione());
        List<FlussoValidazione> flusso = flussoService.findByIdUtente(budget.getFlusso().getIdUtente());
        if(categoria.isPresent()&& categoria.get().getTipoCategoria()== TipoCategoria.BUGET||
           !flusso.isEmpty()){
            try {
                budgetRepository.save(budget);
            }catch (Exception e){
                //TOOD add custom exception
                throw e;
            }
        }else {
            //TOOD add custom exception
            throw new RuntimeException();
        }
        return budget;
    }

    public ResponseEntity<Budget> update(Budget inBudget){
        try{
            Budget newBudget = budgetRepository.findById(inBudget.getId()).orElseThrow(RuntimeException::new);
            newBudget.setNome(inBudget.getNome());
            newBudget.setDataInizio(inBudget.getDataInizio());
            newBudget.setTags(inBudget.getTags());
            newBudget.setCategoria(inBudget.getCategoria());
            newBudget.setDataFine(inBudget.getDataFine());
            newBudget.setImportoAttuale(inBudget.getImportoAttuale());
            newBudget.setImportoStimato(inBudget.getImportoStimato());
            return new  ResponseEntity<>(budgetRepository.save(newBudget),HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public void deleteById(Long id){
        budgetRepository.deleteById(id);
    }

}
