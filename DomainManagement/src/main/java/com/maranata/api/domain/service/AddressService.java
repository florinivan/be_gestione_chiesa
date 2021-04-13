package com.maranata.api.domain.service;

import com.maranata.api.domain.dao.AddressRepository;
import com.maranata.api.domain.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public ResponseEntity<Address> update(Address inAddress){
        try {
            Address newAddress = addressRepository.findById(inAddress.getId()).orElseThrow(RuntimeException::new);
            newAddress.setCity(inAddress.getCity());
            newAddress.setCountry(inAddress.getCountry());
            newAddress.setRegion(inAddress.getRegion());
            newAddress.setDistrict(inAddress.getDistrict());
            newAddress.setStreet(inAddress.getStreet());
            addressRepository.save(newAddress);
            return ResponseEntity.ok(newAddress);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
