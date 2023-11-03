package dao;

import entities.Cat;

import java.util.ArrayList;

public interface CatDao {
    Cat save(Cat cat);
    void deleteById(long id);
    void deleteByEntity(Cat cat);
    void deleteAll();
    Cat update(Cat cat);
    Cat getById(long id);
    ArrayList<Cat> getAll();
}
