package com.kusaikina.lab3.repositories;

import com.kusaikina.lab3.entities.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    Cat save(Cat cat);
    void deleteById(long id);
    void deleteAll();
    Cat getById(long id);
    List<Cat> findAll();
    List<Cat> getAllByName(String name);
    List<Cat> getAllByOwnerId(long id);
}