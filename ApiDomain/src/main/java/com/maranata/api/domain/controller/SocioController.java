package com.maranata.api.domain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.api.domain.dao.SocioRepository;
import com.maranata.api.domain.entity.Membro;
import com.maranata.api.domain.entity.Socio;
import com.maranata.api.domain.service.PersonaService;
import com.maranata.api.domain.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soci")
public class SocioController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    SocioRepository socioRepository;
    @Autowired
    PersonaService personaService;
    @Autowired
    SocioService socioService;

    @GetMapping
    public ResponseEntity<List<Socio>> findAll(){
        List<Socio> socio = socioRepository.findAll();
        return new ResponseEntity<>(socio,HttpStatus.OK);
    }

    @GetMapping("/{id_socio}")
    public ResponseEntity<Socio> findById(@PathVariable Long id){
        Socio socio = socioRepository.findById(id).get();
        return new ResponseEntity<>(socio,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Socio> addSocio(@RequestBody Socio socio) {
        return new ResponseEntity<>(personaService.addPersonaSocio(socio), HttpStatus.OK);
    }

    @PatchMapping(path = "/{id_socio}" )
    public ResponseEntity<Socio>updateSocio(@PathVariable Long id, @RequestBody JsonPatch inSocio){
        try {
            Socio socio = socioRepository.findById(id).orElseThrow(RuntimeException::new);
            Socio socioPatched = applyPatchToCustomer(inSocio, socio);
            socioService.updateSocio(socioPatched);
            return ResponseEntity.ok(socioPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private Socio applyPatchToCustomer(JsonPatch patch, Socio targetSocio) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetSocio, JsonNode.class));
        return objectMapper.treeToValue(patched, Socio.class);
    }

}
