package com.maranata.api.domain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.api.domain.dao.MembroRepository;
import com.maranata.api.domain.entity.Membro;
import com.maranata.api.domain.service.MembroService;
import com.maranata.api.domain.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membri")
public class MembroController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MembroRepository membroRepository;
    @Autowired
    PersonaService personaService;
    @Autowired
    MembroService membroService;

    @GetMapping
    public ResponseEntity<List<Membro>> findAll(){
        List<Membro> membro = membroRepository.findAll();
        return new ResponseEntity<>(membro,HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkMembroByCf(@RequestParam String codiceFiscale) {
        return new ResponseEntity<Boolean>( membroRepository.existMembroBycodiceFiscale(codiceFiscale),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Membro> addMembro(@RequestBody Membro membro){
        return new ResponseEntity<>(membroRepository.save(membro),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Membro> addPersonaMembro( @RequestBody Membro membro) {
        return new ResponseEntity<>(personaService.addPersonaMembro(membro),HttpStatus.OK);
    }


    @PatchMapping(path = "/{id_membro}" )
    public ResponseEntity<Membro>updateMembro(@PathVariable Long id, @RequestBody JsonPatch inMembro){
        try {
            Membro membro = membroRepository.findById(id).orElseThrow(RuntimeException::new);
            Membro membroPatched = applyPatchToCustomer(inMembro, membro);
            membroService.updateMembro(membroPatched);
            return ResponseEntity.ok(membroPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus>deleteMembro(@PathVariable Long id){
        membroRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Membro applyPatchToCustomer(JsonPatch patch, Membro targetMembro) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetMembro, JsonNode.class));
        return objectMapper.treeToValue(patched, Membro.class);
    }


}