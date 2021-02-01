package com.maranata.Api.controller;

import com.maranata.Api.dto.PersonaDto;
import com.maranata.Api.feign.RegistrazioneFeignClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;


@RestController
public class RegistrazioneMembroController {

    @Autowired
    private RegistrazioneFeignClient registrazioneFeignClient;

}
