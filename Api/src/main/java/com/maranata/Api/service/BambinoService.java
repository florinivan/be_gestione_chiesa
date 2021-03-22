package com.maranata.Api.service;

import com.github.fge.jsonpatch.JsonPatch;
import com.maranata.Api.dto.BambinoDto;
import com.maranata.Api.feign.client.BambinoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BambinoService {

    @Autowired
    private BambinoFeignClient bambinoFeignClient;

    public ResponseEntity<Collection<BambinoDto>>bambinoList(){
        return bambinoFeignClient.bambinoList();
    }

    public Boolean bambinoCheck(String codiceFiscale) {
        return bambinoFeignClient.checkBambino(codiceFiscale);
    }

    public ResponseEntity<BambinoDto> bambinoAdd(BambinoDto bambinoDto) {
        return bambinoFeignClient.addBambino(bambinoDto);
    }

    public ResponseEntity<BambinoDto> bambinoUpdate(Long id , JsonPatch bambinoDto){
        return bambinoFeignClient.update(id,bambinoDto);
    }
}
