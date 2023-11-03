package repository.mybatis.mappers;

import entities.Cat;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

public interface CatMapper {
    @Insert({
            "INSERT INTO cats (Name, Date, Breed, Color, OwnerId)" +
            "VALUES (#{name}, #{date}, #{breed}, #{color}, #{ownerId})"
    })
    int save(Cat cat);

    @Delete({"DELETE FROM cats WHERE CatId = #{id}"})
    void deleteById(long id);

    @Delete({"DELETE FROM cats WHERE CatId = #{id}"})
    void deleteByEntity(Cat cat);

    @Delete({"DELETE FROM cats"})
    void deleteAll();

    @Delete({"DELETE FROM cats WHERE OwnerId = #{id}"})
    void deleteByOwnerId(long id);

    @Update({"UPDATE cats SET Name=#{name} Date=#{date} Breed=#{breed} Color=#{color} OwnerId=#{ownerId}" +
            "WHERE CatId=#{id}"})
    Cat update(Cat cat);

    @Select({"SELECT * FROM cats WHERE Id = #{id}"})
    @Results(value = {
            @Result(property = "id", column = "Id"),
            @Result(property = "name", column = "Name"),
            @Result(property = "date", column = "Date"),
            @Result(property = "breed", column = "Breed"),
            @Result(property = "color", column = "Color"),
            @Result(property = "ownerId", column = "OwnerId")
    })
    Cat getById(long id);

    @Select({"SELECT * FROM cats"})
    @Results(value = {
            @Result(property = "id", column = "Id"),
            @Result(property = "name", column = "Name"),
            @Result(property = "date", column = "Date"),
            @Result(property = "breed", column = "Breed"),
            @Result(property = "color", column = "Color"),
            @Result(property = "ownerId", column = "OwnerId")
    })
    ArrayList<Cat> getAll();
}
