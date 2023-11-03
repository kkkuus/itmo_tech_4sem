package com.kusaikina.lab3.controllers;

import com.kusaikina.lab3.dto.FleaDto;
import com.kusaikina.lab3.entities.Flea;
import com.kusaikina.lab3.services.FleasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fleas")
public class FleaController {

    FleasService fleasService;

    public FleaController(FleasService fleasService) {
        this.fleasService = fleasService;
    }

    @GetMapping("/id/{id}")
    public FleaDto getById(@PathVariable long id) {
        return fleasService.getById(id);
    }

    @GetMapping("/all")
    public List<FleaDto> findAll() {
        return fleasService.getAll();
    }

    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable("id") long id) {
        fleasService.deleteById(id);
    }

    @GetMapping("/name/{name}")
    public List<FleaDto> getAllByName(@PathVariable("name") String name) {
        return fleasService.getAllByName(name);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        fleasService.deleteAll();
    }

    @PostMapping("")
    public void save(@RequestBody Flea flea) {
        fleasService.save(flea);
    }
}
