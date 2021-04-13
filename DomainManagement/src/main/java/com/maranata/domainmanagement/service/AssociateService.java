package com.maranata.domainmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maranata.domainmanagement.dao.AssociateRepository;
import com.maranata.domainmanagement.entity.Associate;

@Service
public class AssociateService {

    @Autowired
    AssociateRepository associateRepository;

    public ResponseEntity<Associate> update(Associate inAssociate){
        try {
            Associate newAssociate = associateRepository.findById(inAssociate.getPerson().getId()).orElseThrow(RuntimeException::new);
            newAssociate.setAnnotations(inAssociate.getAnnotations());
            newAssociate.setEmail(inAssociate.getEmail());
            newAssociate.setFlagCd(inAssociate.getFlagCd());
            newAssociate.setPhoneNumber(inAssociate.getPhoneNumber());
            newAssociate.setFlagStatus(inAssociate.getFlagStatus());
            newAssociate.setRegistrationDate(inAssociate.getRegistrationDate());
            newAssociate.setPerson(inAssociate.getPerson());
            associateRepository.save(newAssociate);
            return ResponseEntity.ok(newAssociate);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
