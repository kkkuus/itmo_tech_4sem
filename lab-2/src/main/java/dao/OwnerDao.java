package dao;

import entities.Cat;
import entities.Owner;

import java.util.ArrayList;

public interface OwnerDao {
    Owner save(Owner owner);
    void deleteById(long id);
    void deleteByEntity(Owner owner);
    void deleteAll();
    Owner update(Owner owner);
    Owner getById(long id);
    ArrayList<Owner> getAll();
    ArrayList<Cat> getAllByOwnerId(long id);
}
