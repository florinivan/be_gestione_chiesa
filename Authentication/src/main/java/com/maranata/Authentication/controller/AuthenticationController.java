package com.maranata.Authentication.controller;

import com.maranata.Authentication.entity.Account;
import com.maranata.Authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;


    @PostMapping("/accounts")
    public ResponseEntity createCustomer(@RequestBody Account account ) {
        return new ResponseEntity<>(authenticationService.generateJWTToken(account.getUsername(), account.getPassword()), HttpStatus.OK);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


}
