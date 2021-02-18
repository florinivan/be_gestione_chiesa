package com.maranata.Api.service;

import com.maranata.Api.dto.MembroDto;
import com.maranata.Api.feign.client.MembroFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MembroService {

    @Autowired
    private MembroFeignClient membroFeignClient;

    public ResponseEntity<Collection<MembroDto>> membroList(){
        return membroFeignClient.membroList();
    }

    public MembroDto membroPersonaAdd(MembroDto membroDto) {
        return membroFeignClient.membroPersonaAdd(membroDto);
    }

    public Boolean membroCheck(String codiceFiscale) {
        return membroFeignClient.checkMembro(codiceFiscale);
    }

    public MembroDto membroAdd(MembroDto membroDto) {
        return membroFeignClient.membroAdd(membroDto);
    }

    public MembroDto membroUpdate(MembroDto membroDto,Long id){
        return membroFeignClient.membroUpdate(membroDto,id);
    }
}
