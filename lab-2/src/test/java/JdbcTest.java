import entities.Cat;
import entities.Owner;
import entities.enums.CatsColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.jdbc.CatJdbcService;
import repository.jdbc.OwnerJdbcService;
import services.CatsService;
import services.OwnersService;

import java.util.ArrayList;
import java.util.Date;

public class JdbcTest {
    OwnerJdbcService ownerJdbc = new OwnerJdbcService();
    CatJdbcService catJdbc = new CatJdbcService();
    OwnersService ownersService = new OwnersService(ownerJdbc);
    CatsService catsService = new CatsService(catJdbc);

    @Test
    public void getOwner() {
        Owner owner = ownersService.getById(1634);
        Assertions.assertEquals(1634, owner.getId());
        Assertions.assertEquals("Name0", owner.getName());
    }

    //отработал за 2 sec 250 ms
    @Test
    public void comparisonOwners(){
        ownersService.deleteAll();
        Owner owner;
        for (int i = 0; i < 100; ++i) {
            owner = new Owner("Name" + i, new Date(17-2-2003));
            ownersService.save(owner);
        }

        ArrayList<Owner> owners = ownersService.getAll();
        Assertions.assertEquals(100, owners.size());
    }

    //отработал за 3 sec 398 ms
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
}
