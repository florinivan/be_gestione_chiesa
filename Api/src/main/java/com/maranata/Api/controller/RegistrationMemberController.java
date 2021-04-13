package com.maranata.Api.controller;

import com.maranata.Api.dto.RegistrationMemberDto;

import com.maranata.Api.service.RegistrationMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/registration")
@RestController
public class RegistrationMemberController {

    @Autowired
    RegistrationMemberService registrationMemberService;

    @PostMapping
    public RegistrationMemberDto registrationMembro(@RequestBody RegistrationMemberDto registrationMemberDto){
        return registrationMemberService.memberRegistration(registrationMemberDto);
    }


}
