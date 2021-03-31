package com.maranata.Api.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.maranata.Api.dto.ChildDto;
import com.maranata.Api.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/child")
public class ChildController {

    @Autowired
    ChildService childService;

    @GetMapping("/list")
    public Collection<ChildDto> childList(Model model) {
        model.addAttribute("children", childService.childList().getBody());
        return childService.childList().getBody();
    }

    @GetMapping("/edit")
    public boolean childOne(@RequestParam String personalNumber, Model model) {
        model.addAttribute("personalNumber", childService.checkChild(personalNumber));
        return childService.checkChild(personalNumber);
    }

    @PostMapping("/add")
    public ResponseEntity<ChildDto> childAdd(@RequestBody ChildDto childDto){
        return childService.childAdd(childDto);
    }

    @PatchMapping("/update")
    public ResponseEntity<ChildDto> childUpdate (@PathVariable Long id, @RequestBody JsonPatch childDto){
        return childService.childUpdate(id,childDto);
    }
}
