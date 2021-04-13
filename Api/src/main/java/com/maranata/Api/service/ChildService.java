package com.maranata.Api.service;

import com.github.fge.jsonpatch.JsonPatch;
import com.maranata.Api.dto.ChildDto;
import com.maranata.Api.feign.client.ChildFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ChildService {

    @Autowired
    private ChildFeignClient childFeignClient;

    public ResponseEntity<Collection<ChildDto>> childList(){
        return childFeignClient.childList();
    }

    public Boolean checkChild(String personalNumber) {
        return childFeignClient.checkChild(personalNumber);
    }

    public ResponseEntity<ChildDto> childAdd(ChildDto childDto) {
        return childFeignClient.addChild(childDto);
    }

    public ResponseEntity<ChildDto> childUpdate(Long id , JsonPatch childDto){
        return childFeignClient.update(id,childDto);
    }
}
