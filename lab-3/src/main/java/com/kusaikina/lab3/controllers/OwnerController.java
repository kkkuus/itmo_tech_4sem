package com.kusaikina.lab3.controllers;

import com.kusaikina.lab3.dto.CatDto;
import com.kusaikina.lab3.dto.OwnerDto;
import com.kusaikina.lab3.entities.Owner;
import com.kusaikina.lab3.services.OwnersService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("owners")
public class OwnerController {

    OwnersService ownersService;

    public OwnerController(OwnersService ownersService) {
        this.ownersService = ownersService;
    }

    @GetMapping("id/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public OwnerDto getById(@PathVariable long id) {
        return ownersService.getById(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('READ')")
    public List<OwnerDto> findAll() {
        return ownersService.getAll();
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('READ')")
    public List<OwnerDto> getAllByName(@PathVariable("name") String name) {
        return ownersService.getAllByName(name);
    }

    @GetMapping("/allCats/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public List<CatDto> getAllCatsByOwnerId(@PathVariable long id) {
        return ownersService.getAllCatsByOwnerId(id);
    }

    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public void deleteById(@PathVariable("id") long id) {
        ownersService.deleteById(id);
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasAuthority('WRITE')")
    public void deleteAll() {
        ownersService.deleteAll();
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('WRITE')")
    public OwnerDto save(@RequestBody Owner owner) {
        return ownersService.save(owner);
    }
}