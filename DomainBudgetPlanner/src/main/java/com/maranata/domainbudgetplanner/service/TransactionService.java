package com.maranata.domainbudgetplanner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maranata.commonbean.budgetplanner.entity.Category;
import com.maranata.commonbean.budgetplanner.entity.CategoryType;
import com.maranata.commonbean.budgetplanner.entity.Transaction;
import com.maranata.commonbean.budgetplanner.entity.Validation;
import com.maranata.domainbudgetplanner.dao.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ValidationService validationService;
    @Autowired
    CategoryService categoryService;


    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }


    public List <Transaction> findTransactionsBytypeAndDateBetween(Long validationId, CategoryType type, Long startDate, Long endDate){
        return transactionRepository.findTransactionsBytypeAndDateBetween(validationId,type.name(),startDate,endDate);
    }


    public Optional<Transaction> findbyId(Long id){
        return transactionRepository.findById(id);
    }

    public List<Transaction> findByPeriod(Long startDate, Long endDate) {
        return transactionRepository.findByPeriod(startDate, endDate);
    }


    public Transaction addTransaction(Transaction transaction){
        Optional<Category> category = categoryService.findByDescription(transaction.getCategory().getDescription());
        List<Validation> validations = validationService.findByUserId(transaction.getValidation().getUserId());
        if(category.isPresent()|| !validations.isEmpty())
        {
            try {
                transactionRepository.save(transaction);
            }catch (Exception e){
                //TOOD add custom exception
                throw e;
            }
        }else {
            //TOOD add custom exception
            throw new RuntimeException();
        }
        return transaction;
    }


    public ResponseEntity<Transaction> update(Transaction inTransaction){
        try{
            Transaction newTransaction = transactionRepository.findById(inTransaction.getId()).orElseThrow(RuntimeException::new);
            newTransaction.setDate(inTransaction.getDate());
            newTransaction.setDescription(inTransaction.getDescription());
            newTransaction.setCategory(inTransaction.getCategory());
            newTransaction.setTags(inTransaction.getTags());
            newTransaction.setAmount(inTransaction.getAmount());
            newTransaction.setValidation(inTransaction.getValidation());
            transactionRepository.save(newTransaction);
            return ResponseEntity.ok(newTransaction);
        }catch (Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    }

    public void  deleteById (Long id){
        transactionRepository.deleteById(id);
    }

}
