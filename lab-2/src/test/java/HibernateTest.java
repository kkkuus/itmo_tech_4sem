import entities.Cat;
import entities.Owner;

import entities.enums.CatsColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.hibernate.CatHibernateService;
import repository.hibernate.OwnerHibernateService;
import services.CatsService;
import services.OwnersService;

import java.util.ArrayList;
import java.util.Date;


public class HibernateTest {
    OwnerHibernateService ownerHibernateService = new OwnerHibernateService();
    CatHibernateService catHibernateService = new CatHibernateService();
    OwnersService ownersService = new OwnersService(ownerHibernateService);
    CatsService catsService = new CatsService(catHibernateService);
    @Test
    public void getOwner() {
        Owner temp_owner = ownersService.getById(832);
        Assertions.assertEquals(temp_owner.getId(), 832);
        Assertions.assertEquals(temp_owner.getName(), "Name0");
    }

    //отработал за 12 sec 183 ms
    @Test
    public void comparisonOwners(){
        ownersService.deleteAll();
        Owner owner;
        for (int i = 0; i < 100; ++i) {
            owner = new Owner("Name" + i, new Date(17-2-2003));
            ownersService.save(owner);
        }
        ArrayList<Owner> owners = ownersService.getAll();;
        Assertions.assertEquals(100, owners.size());
    }

    //выполнил за 11 sec 716 ms
    @Test
    public void comparisonCats() {
        Cat cat;
        catsService.deleteAll();
        Owner owner = ownersService.getById(1634);
        for (int i = 1; i <= 100; ++i) {
            cat = new Cat("Cat" + i, new Date(20-1-2023), "breed", CatsColor.black, owner);
            catsService.save(cat);
        }

        ArrayList<Cat> cats = catsService.getAll();
        Assertions.assertEquals(100, cats.size());
    }

    @Test
    public void deleteOwner(){
        ArrayList<Owner> owners = ownersService.getAll();
        ArrayList<Cat> cats = catsService.getAll();
        ownersService.deleteById(1);
        owners = ownersService.getAll();
        cats = catsService.getAll();
        Assertions.assertEquals(99, owners.size());
        Assertions.assertEquals(90, cats.size());
    }
}
