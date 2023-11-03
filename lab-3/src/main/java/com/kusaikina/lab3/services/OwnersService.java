package com.kusaikina.lab3.services;

import com.kusaikina.lab3.dto.CatDto;
import com.kusaikina.lab3.dto.OwnerDto;
import com.kusaikina.lab3.entities.Cat;
import com.kusaikina.lab3.entities.Owner;
import org.springframework.stereotype.Service;
import com.kusaikina.lab3.repositories.OwnerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnersService {
    private OwnerRepository ownerRepository;

    public OwnersService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public OwnerDto save(Owner owner) {
        Owner tempOwner = this.ownerRepository.save(owner);
        return new OwnerDto(tempOwner);
    }
    public void deleteById(long id) {
        this.ownerRepository.deleteById(id);
    }

    public void deleteAll() {
        this.ownerRepository.deleteAll();
    }
    public OwnerDto getById(long id) {
        Owner owner = this.ownerRepository.getById(id);
        return new OwnerDto(owner);
    }
    public List<OwnerDto> getAll() {
        List<Owner> tempOwners = this.ownerRepository.findAll();
        List<OwnerDto> owners = new ArrayList<OwnerDto>();
        for (Owner owner: tempOwners) {
            owners.add(new OwnerDto(owner));
        }
        return owners;
    }

    public List<CatDto> getAllCatsByOwnerId(long id) {
        List<Cat> tempCats = this.ownerRepository.getAllCatsByOwnerId(id);
        List<CatDto> cats = new ArrayList<CatDto>();
        for (Cat cat: tempCats) {
            cats.add(new CatDto(cat));
        }
        return cats;
    }

    public List<OwnerDto> getAllByName(String name) {
        List<Owner> tempOwners = this.ownerRepository.getAllByName(name);
        List<OwnerDto> owners = new ArrayList<OwnerDto>();
        for (Owner owner: tempOwners) {
            owners.add(new OwnerDto(owner));
        }
        return owners;
    }
}
