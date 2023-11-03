import entities.Cat;
import entities.Owner;
import entities.enums.CatsColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.mybatis.CatMybatisService;
import repository.mybatis.OwnerMybatisService;
import services.CatsService;
import services.OwnersService;

import java.util.ArrayList;
import java.util.Date;

public class MyBatisTest {
    OwnerMybatisService ownerMybatisService = new OwnerMybatisService();
    CatMybatisService catMybatisService = new CatMybatisService();
    OwnersService ownersService = new OwnersService(ownerMybatisService);
    CatsService catsService = new CatsService(catMybatisService);
    @Test
    public void getOwner() {
        Owner owner = ownersService.getById(1433);
        Assertions.assertEquals(1433, owner.getId());
        Assertions.assertEquals("Name99", owner.getName());
    }

    //отработал за 3 sec 822 ms
    @Test
    public void comparisonOwners() {
        ownersService.deleteAll();
        Owner owner;
        for (int i = 0; i < 100; ++i) {
            owner = new Owner("Name" + i, new Date(17-2-2003));
            ownersService.save(owner);
        }
        ArrayList<Owner> owners = ownersService.getAll();
        Assertions.assertEquals(100, owners.size());
    }

    //отработал за 3 sec 948 ms
    @Test
    public void comparisonCats() {
        Cat cat;
        Owner owner = ownersService.getById(1634);
        catsService.deleteAll();
        for(int i = 0; i < 100; ++i) {
            cat = new Cat("Cat" + i, new Date(20-1-2023), "breed", CatsColor.black, owner);
            catsService.save(cat);
        }

        ArrayList<Cat> cats = catsService.getAll();
        Assertions.assertEquals(100, cats.size());
    }
}
