package com.maranata.Api.controller;

import com.maranata.Api.dto.BambinoDto;
import com.maranata.Api.dto.MembroDto;
import com.maranata.Api.feign.client.BambinoFeignClient;
import com.maranata.Api.feign.client.MembroFeignClient;
import com.maranata.Api.service.RegistrazioneMembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/bambino")
public class BambinoController {

    @Autowired
    private BambinoFeignClient bambinoFeignClient;
    @Autowired
    RegistrazioneMembroService registrazioneMembroService;

    @GetMapping("/list")
    public Collection<BambinoDto> bambinoList(Model model) {
        model.addAttribute("membri", bambinoFeignClient.bambinoList().getBody());
        return bambinoFeignClient.bambinoList().getBody();
    }

    @GetMapping("/edit")
    public boolean bambinoOne(@RequestParam String codiceFiscale, Model model) {
        model.addAttribute("codiceFiscale", registrazioneMembroService.bambinoCheck(codiceFiscale));
        return registrazioneMembroService.bambinoCheck(codiceFiscale);
    }

    @PostMapping("/add")
    public BambinoDto bambinoAdd(@RequestBody BambinoDto bambinoDto){
        return registrazioneMembroService.bambinoAdd(bambinoDto);
    }

}
