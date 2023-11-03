package com.kusaikina.lab3.controllers;

import com.kusaikina.lab3.dto.CatDto;
import com.kusaikina.lab3.entities.Cat;
import com.kusaikina.lab3.security.CatsFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;


import com.kusaikina.lab3.services.CatsService;

import java.util.List;

@RestController
@RequestMapping("cats")
public class CatController {

    private final CatsService catsService;
    private final CatsFilter catsFilter;

    public CatController(CatsService catsService, CatsFilter catsFilter) {
        this.catsService = catsService;
        this.catsFilter = catsFilter;
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public CatDto getById(@PathVariable long id) {
        return catsService.getById(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('READ')")
    public List<CatDto> findAll() {
        return catsService.getAll();
        //return catsFilter.catFilter(SecurityContextHolder.getContext().getAuthentication().getName(), catsService.getAll());
    }

    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public void deleteById(@PathVariable("id") long id) {
        catsService.deleteById(id);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('READ')")
    public List<CatDto> getAllByName(@PathVariable("name") String name) {
        return catsFilter.catFilter(SecurityContextHolder.getContext().getAuthentication().getName(), catsService.getAllByName(name));
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasAuthority('WRITE')")
    public void deleteAll() {
        catsService.deleteAll();
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('WRITE')")
    public void save(@RequestBody Cat cat) {
        catsService.save(cat);
    }

    @GetMapping("/all/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public List<CatDto> getAllCatsByOwnerId(@PathVariable long id) {
        return catsService.getAllByOwnerId(id);
    }
}