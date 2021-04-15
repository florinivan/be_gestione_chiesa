package com.maranata.apimanagement.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.maranata.apimanagement.service.ChildService;

import com.maranata.commonbean.management.entity.Child;
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
    public Collection<Child> childList(Model model) {
        model.addAttribute("children", childService.childList().getBody());
        return childService.childList().getBody();
    }

    @GetMapping("/edit")
    public boolean childOne(@RequestParam String personalNumber, Model model) {
        model.addAttribute("personalNumber", childService.checkChild(personalNumber));
        return childService.checkChild(personalNumber);
    }

    @PostMapping("/add")
    public ResponseEntity<Child> childAdd(@RequestBody Child child){
        return childService.childAdd(child);
    }

    @PatchMapping("/update")
    public ResponseEntity<Child> childUpdate (@PathVariable Long id, @RequestBody JsonPatch child){
        return childService.childUpdate(id,child);
    }
}
