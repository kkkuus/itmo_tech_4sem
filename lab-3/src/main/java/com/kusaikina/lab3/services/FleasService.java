package com.kusaikina.lab3.services;

import com.kusaikina.lab3.dto.FleaDto;
import com.kusaikina.lab3.entities.Flea;
import com.kusaikina.lab3.repositories.FleaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FleasService {
    private FleaRepository fleaRepository;

    public FleasService(FleaRepository fleaRepository) {
        this.fleaRepository = fleaRepository;
    }

    public FleaDto save(Flea flea) {
        Flea tempFlea = this.fleaRepository.save(flea);
        return new FleaDto(tempFlea);
    }
    public void deleteById(long id) {
        this.fleaRepository.deleteById(id);
    }

    public void deleteAll() {
        this.fleaRepository.deleteAll();
    }
    public FleaDto getById(long id) {
        Flea flea = this.fleaRepository.getById(id);
        return new FleaDto(flea);
    }

    public List<FleaDto> getAll() {
        List<Flea> tempFleas = this.fleaRepository.findAll();
        List<FleaDto> fleas = new ArrayList<>();
        for (Flea flea: tempFleas) {
            fleas.add(new FleaDto(flea));
        }
        return fleas;
    }

    public List<FleaDto> getAllByName(String name) {
        List<Flea> tempFleas = this.fleaRepository.getAllByName(name);
        List<FleaDto> fleas = new ArrayList<>();
        for (Flea flea: tempFleas) {
            fleas.add(new FleaDto(flea));
        }
        return fleas;
    }
}
