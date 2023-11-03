package com.kusaikina.lab3.repositories;

import com.kusaikina.lab3.entities.Cat;
import com.kusaikina.lab3.entities.Flea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FleaRepository extends JpaRepository<Flea, Long> {
    Flea save(Flea flea);
    void deleteById(long id);
    void deleteAll();
    Flea getById(long id);
    List<Flea> findAll();
    List<Flea> getAllByName(String name);
}
