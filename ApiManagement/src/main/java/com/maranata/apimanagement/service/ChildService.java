package com.maranata.apimanagement.service;

import com.github.fge.jsonpatch.JsonPatch;
import com.maranata.apimanagement.feign.client.ChildFeignClient;

import com.maranata.commonbean.management.entity.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ChildService {

    @Autowired
    private ChildFeignClient childFeignClient;

    public ResponseEntity<Collection<Child>> childList(){
        return childFeignClient.childList();
    }

    public Boolean checkChild(String personalNumber) {
        return childFeignClient.checkChild(personalNumber);
    }

    public ResponseEntity<Child> childAdd(Child child) {
        return childFeignClient.addChild(child);
    }

    public ResponseEntity<Child> childUpdate(Long id , JsonPatch child){
        return childFeignClient.update(id,child);
    }
}
