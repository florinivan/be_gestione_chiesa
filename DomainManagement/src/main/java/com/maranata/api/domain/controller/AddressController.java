package com.maranata.api.domain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maranata.api.domain.dao.AddressRepository;
import com.maranata.api.domain.entity.Address;
import com.maranata.api.domain.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adress")
public class AddressController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>>findAll(){
        List<Address> address = addressRepository.findAll();
        return  new ResponseEntity<>(address,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> findByid(@PathVariable Long id){
        Address address = addressRepository.findById(id).get();
        return new ResponseEntity<>(address,HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}" )
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody JsonPatch inResidenza){
        try {
            Address address = addressRepository.findById(id).orElseThrow(RuntimeException::new);
            Address addressPatched = applyPatchToCustomer(inResidenza, address);
            addressService.update(addressPatched);
            return ResponseEntity.ok(addressPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Address>deleteById(@PathVariable Long id){
        addressRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Address applyPatchToCustomer(JsonPatch patch, Address targetAddress) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetAddress, JsonNode.class));
        return objectMapper.treeToValue(patched, Address.class);
    }
}
