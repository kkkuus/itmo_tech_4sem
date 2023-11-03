package services;

import dao.CatDao;
import entities.Cat;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CatsService {
    private CatDao cat;

    public CatsService(CatDao catDao) {
        this.cat = catDao;
    }

    public Cat save(Cat cat) {
        return this.cat.save(cat);
    }
    public void deleteById(long id) {
        this.cat.deleteById(id);
    }
    public void deleteByEntity(Cat cat) {
        this.cat.deleteByEntity(cat);
    }
    public void deleteAll() {
        this.cat.deleteAll();
    }
    public Cat update(Cat cat) {
        return this.cat.update(cat);
    }
    public Cat getById(long id) {
        return this.cat.getById(id);
    }
    public ArrayList<Cat> getAll() {
        return this.cat.getAll();
    }
}
