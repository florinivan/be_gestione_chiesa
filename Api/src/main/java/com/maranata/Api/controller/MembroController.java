package com.maranata.Api.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.maranata.Api.dto.MembroDto;
import com.maranata.Api.service.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<MembroDto> membroAdd(@RequestBody MembroDto membroDto){
        return new ResponseEntity<>(membroService.membroAdd(membroDto), HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<MembroDto> membroPersonaAdd(@RequestBody MembroDto membroDto){
        return new ResponseEntity<>(membroService.membroPersonaAdd(membroDto),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MembroDto> membroUpdate(@RequestBody JsonPatch membroDto, @PathVariable Long id){
        return  membroService.membroUpdate(membroDto,id);
    }
}
