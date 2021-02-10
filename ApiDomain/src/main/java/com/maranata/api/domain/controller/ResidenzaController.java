package com.maranata.api.domain.controller;

import com.maranata.api.domain.dao.ResidenzaRepository;
import com.maranata.api.domain.entity.Residenza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residenza")
public class ResidenzaController {
    @Autowired
    ResidenzaRepository residenzaRepository;

    @GetMapping
    public ResponseEntity<List<Residenza>>findAll(){
        List<Residenza> residenza = residenzaRepository.findAll();
        return  new ResponseEntity<>(residenza,HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<Residenza> addResidenza(Residenza residenza){
        residenzaRepository.save(residenza);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Residenza>deleteById(@PathVariable Long id){
        residenzaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
