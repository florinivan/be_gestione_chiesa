package com.maranata.apimanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maranata.apimanagement.dto.RegistrationMemberDto;
import com.maranata.apimanagement.service.RegistrationMemberService;

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
