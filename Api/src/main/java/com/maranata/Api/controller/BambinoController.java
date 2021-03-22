package com.maranata.Api.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.maranata.Api.dto.BambinoDto;
import com.maranata.Api.service.BambinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/bambino")
public class BambinoController {

    @Autowired
    BambinoService bambinoService;

    @GetMapping("/list")
    public Collection<BambinoDto> bambinoList(Model model) {
        model.addAttribute("membri", bambinoService.bambinoList().getBody());
        return bambinoService.bambinoList().getBody();
    }

    @GetMapping("/edit")
    public boolean bambinoOne(@RequestParam String codiceFiscale, Model model) {
        model.addAttribute("codiceFiscale", bambinoService.bambinoCheck(codiceFiscale));
        return bambinoService.bambinoCheck(codiceFiscale);
    }

    @PostMapping("/add")
    public ResponseEntity<BambinoDto> bambinoAdd(@RequestBody BambinoDto bambinoDto){
        return bambinoService.bambinoAdd(bambinoDto);
    }

    @PatchMapping("/update")
    public ResponseEntity<BambinoDto> updateBambino(@PathVariable Long id, @RequestBody JsonPatch bambinoDto){
        return bambinoService.bambinoUpdate(id,bambinoDto);
    }
}
