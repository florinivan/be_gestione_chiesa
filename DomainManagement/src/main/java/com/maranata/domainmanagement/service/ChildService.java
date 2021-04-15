package com.maranata.domainmanagement.service;

import com.maranata.commonbean.management.entity.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maranata.domainmanagement.dao.ChildRepository;


@Service
public class ChildService {

    @Autowired
    ChildRepository childRepository;

    public ResponseEntity<Child> update(Child inChild){
        try {
            Child newChild = childRepository.findById(inChild.getPerson().getId()).orElseThrow(RuntimeException::new);
            newChild.setPerson(inChild.getPerson());
            newChild.setFatherPersonalNumber(inChild.getFatherPersonalNumber());
            newChild.setMatherPersonalNumber(inChild.getMatherPersonalNumber());
            childRepository.save(newChild);
            return ResponseEntity.ok(newChild);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
