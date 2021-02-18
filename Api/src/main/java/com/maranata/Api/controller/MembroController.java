package com.maranata.Api.controller;

import com.maranata.Api.dto.MembroDto;
import com.maranata.Api.service.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RequestMapping("membro")
@RestController
public class MembroController {

    @Autowired
    private MembroService membroService;

    @GetMapping("/list")
    public Collection<MembroDto> membroList(Model model) {
        model.addAttribute("membri", membroService.membroList().getBody());
        return membroService.membroList().getBody();
    }

    @GetMapping("/edit")
    public boolean membroOne(@RequestParam String codiceFiscale, Model model) {
        model.addAttribute("codiceFiscale", membroService.membroCheck(codiceFiscale));
        return membroService.membroCheck(codiceFiscale);
    }

    @PostMapping("/addMembro")
    public MembroDto membroAdd(@RequestBody MembroDto membroDto){
        return membroService.membroAdd(membroDto);
    }

    @PostMapping("/add")
    public MembroDto membroPersonaAdd(@RequestBody MembroDto membroDto){
        return membroService.membroPersonaAdd(membroDto);
    }

    @PutMapping
    public MembroDto membroUpdate(@RequestBody MembroDto membroDto,@PathVariable Long id){
        return  membroService.membroUpdate(membroDto,id);
    }
}
