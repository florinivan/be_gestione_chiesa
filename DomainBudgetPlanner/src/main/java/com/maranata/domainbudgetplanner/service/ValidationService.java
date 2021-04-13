package com.maranata.domainbudgetplanner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maranata.commonbean.budgetplanner.entity.Validation;
import com.maranata.domainbudgetplanner.dao.ValidationRepository;
import com.maranata.domainbudgetplanner.dao.ValidationTypeRepository;

@Service
public class ValidationService {

    @Autowired
    ValidationRepository validationRepository;
    @Autowired
    ValidationTypeRepository validationTypeRepository;

    public List<Validation> findAll(){
        return validationRepository.findAll();
    }

    public Optional<Validation> findById(Long id){
        return validationRepository.findById(id);
    }

    public List<Validation> findByUserId(Long userId){
        return validationRepository.findByUserId(userId);
    }

    public Validation add(Validation validation){
        validationTypeRepository.save(validation.getValidationType());
        return validationRepository.save(validation);
    }

    public ResponseEntity<Validation> update(Validation inValidation){
        try{
            Validation newValidation = validationRepository.findById(inValidation.getId()).orElseThrow(RuntimeException::new);
            newValidation.setUserId(inValidation.getId());
            newValidation.setValidationType(inValidation.getValidationType());
            validationRepository.save(newValidation);
            return ResponseEntity.ok(newValidation);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

     public void deleteById(Long id){
         validationRepository.deleteById(id);
     }
}
