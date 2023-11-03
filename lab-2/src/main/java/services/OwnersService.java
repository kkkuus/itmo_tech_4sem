package services;

import dao.OwnerDao;
import entities.Cat;
import entities.Owner;
import lombok.Data;

import java.util.ArrayList;
@Data
public class OwnersService {
    private OwnerDao owner;

    public OwnersService(OwnerDao owner) {
        this.owner = owner;
    }

    public Owner save(Owner owner) {
        return this.owner.save(owner);
    }
    public void deleteById(long id) {
        this.owner.deleteById(id);
    }
    public void deleteByEntity(Owner owner) {
        this.owner.deleteByEntity(owner);
    }
    public void deleteAll() {
        this.owner.deleteAll();
    }
    public Owner update(Owner owner) {
        return this.owner.update(owner);
    }
    public Owner getById(long id) {
        return this.owner.getById(id);
    }
    public ArrayList<Owner> getAll() {
        return this.owner.getAll();
    }
    public ArrayList<Cat> getAllByOwnerId(long id) {
        return this.owner.getAllByOwnerId(id);
    }
}
