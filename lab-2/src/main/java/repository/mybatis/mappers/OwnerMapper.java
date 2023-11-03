package repository.mybatis.mappers;

import entities.Cat;
import entities.Owner;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

public interface OwnerMapper {
    @Insert({"INSERT INTO owners (Name, Date)",
            "VALUES (#{name}, #{date})"})
    int save(Owner owner);

    @Delete({"DELETE FROM owners WHERE OwnerId = #{id}"})
    void deleteById(long id);

    @Delete({"DELETE FROM owners WHERE OwnerId = #{id}"})
    void deleteByEntity(Owner owner);

    @Delete({"DELETE FROM owners"})
    void deleteAll();

    @Update({"UPDATE owners SET Name=#{name} Date=#{date} WHERE OwnerId=#{id}"})
    Owner update(Owner owner);

    @Select({"SELECT * FROM owners WHERE OwnerId = #{id}"})
    @Results(value = {
            @Result(property = "id", column = "OwnerId"),
            @Result(property = "name", column = "Name"),
            @Result(property = "date", column = "Date")
    })
    Owner getById(long id);

    @Select({"SELECT * FROM owners"})
    @Results(value = {
            @Result(property = "id", column = "OwnerId"),
            @Result(property = "name", column = "Name"),
            @Result(property = "date", column = "Date")
    })
    ArrayList<Owner> getAll();

    @Select({"SELECT * FROM cats WHERE Owner = #{id}"})
    @Results(value = {
            @Result(property = "id", column = "CatId"),
            @Result(property = "name", column = "Name"),
            @Result(property = "date", column = "Date"),
            @Result(property = "breed", column = "Breed"),
            @Result(property = "color", column = "Color"),
            @Result(property = "ownerId", column = "OwnerId")
    })
    ArrayList<Cat> getAllByOwnerId(long id);
}
