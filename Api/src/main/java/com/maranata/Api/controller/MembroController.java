package com.maranata.Api.controller;

import com.maranata.Api.dto.MembroDto;
import com.maranata.Api.feign.client.MembroFeignClient;
import com.maranata.Api.service.RegistrazioneMembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RequestMapping("membro")
@RestController
public class MembroController {

    @Autowired
    private MembroFeignClient membroFeignClient;
    @Autowired
    RegistrazioneMembroService registrazioneMembroService;

    @GetMapping("/list")
    public Collection<MembroDto> membroList(Model model) {
        model.addAttribute("membri", membroFeignClient.membroList().getBody());
        return membroFeignClient.membroList().getBody();
    }

    @GetMapping("/edit")
    public boolean membroOne(@RequestParam String codiceFiscale, Model model) {
        model.addAttribute("codiceFiscale", registrazioneMembroService.membroCheck(codiceFiscale));
        return registrazioneMembroService.membroCheck(codiceFiscale);
    }

    @PostMapping("/add")
    public MembroDto membroAdd(@RequestBody MembroDto membroDto){
        return registrazioneMembroService.membroAdd(membroDto);
    }
}
