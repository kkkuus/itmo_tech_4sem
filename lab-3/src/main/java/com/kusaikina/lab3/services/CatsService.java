package com.kusaikina.lab3.services;

import com.kusaikina.lab3.dto.CatDto;
import com.kusaikina.lab3.entities.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kusaikina.lab3.repositories.CatRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatsService {
    private CatRepository catRepository;

    public CatsService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public CatDto save(Cat cat) {
        Cat tempCat = this.catRepository.save(cat);
        return new CatDto(tempCat);
    }
    public void deleteById(long id) {
        this.catRepository.deleteById(id);
    }

    public void deleteAll() {
        this.catRepository.deleteAll();
    }
    public CatDto getById(long id) {
        Cat cat = this.catRepository.getById(id);
        return new CatDto(cat);
    }
    public List<CatDto> getAll() {
        List<Cat> tempCats = this.catRepository.findAll();
        List<CatDto> cats = new ArrayList<>();
        for (Cat cat: tempCats) {
            cats.add(new CatDto(cat));
        }
        return cats;
    }

    public List<CatDto> getAllByName(String name) {
        List<Cat> tempCats = this.catRepository.getAllByName(name);
        List<CatDto> cats = new ArrayList<>();
        for (Cat cat: tempCats) {
            cats.add(new CatDto(cat));
        }
        return cats;
    }

    public List<CatDto> getAllByOwnerId(long ownerId) {
        List<Cat> tempCats = this.catRepository.getAllByOwnerId(ownerId);
        List<CatDto> cats = new ArrayList<>();
        for (Cat cat: tempCats) {
            cats.add(new CatDto(cat));
        }
        return cats;
    }
}
