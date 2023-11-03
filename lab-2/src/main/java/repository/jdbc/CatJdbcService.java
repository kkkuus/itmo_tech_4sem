package repository.jdbc;

import dao.CatDao;
import database.JdbcUtil;
import entities.Cat;
import entities.Owner;
import entities.enums.CatsColor;
import java.sql.*;
import java.util.ArrayList;

public class CatJdbcService implements CatDao {

    public Cat save(Cat cat) {
        Connection connection;
        try {
            connection = JdbcUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO cats (CatId, Name, Date, Breed, Color, OwnerId)  " +
                            "VALUES(?, ?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, cat.getId());
            preparedStatement.setString(2, cat.getName());
            preparedStatement.setDate(3, new java.sql.Date(cat.getDate().getTime()));
            preparedStatement.setString(4, cat.getBreed());
            preparedStatement.setString(5, String.valueOf(cat.getColor()));
            preparedStatement.setLong(6, cat.getOwner().getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cat;
    }

    public void deleteById(long id) {
        Connection connection;
        try {
            connection = JdbcUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM cats WHERE CatId = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByEntity(Cat cat) {
        Connection connection;
        try {
            connection = JdbcUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM cats WHERE CatId = ?");
            preparedStatement.setLong(1, cat.getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        Connection connection;
        try {
            connection = JdbcUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM cats");
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cat update(Cat cat) {
        Connection connection;
        try {
            connection = JdbcUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE cats SET Name = ?, Date = ?, Breed = ?, Color = ?, OwnerId = ?" +
                            "WHERE CatId = ?");
            preparedStatement.setString(1, cat.getName());
            preparedStatement.setDate(2, (Date) cat.getDate());
            preparedStatement.setString(3, cat.getBreed());
            preparedStatement.setString(4, String.valueOf(cat.getColor()));
            preparedStatement.setLong(5, cat.getOwner().getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cat;
    }

    public Cat getById(long id) {
        Connection connection;
        try {
            connection = JdbcUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM cats WHERE CatId = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Cat cat = new Cat();
            cat.setId(resultSet.getLong(1));
            cat.setName(resultSet.getString(2));
            cat.setDate(resultSet.getDate(3));
            cat.setBreed(resultSet.getString(4));
            cat.setColor(CatsColor.valueOf(resultSet.getString(5)));
            Owner owner = new OwnerJdbcService().getById(resultSet.getLong(6));
            cat.setOwner(owner);
            connection.close();
            return cat;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Cat> getAll() {
        Connection connection;
        try {
            connection = JdbcUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM cats");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Cat> cats = new ArrayList<Cat>();
            Cat cat;
            while (resultSet.next()) {
                cat = new Cat();
                cat.setId(resultSet.getLong(1));
                cat.setName(resultSet.getString(2));
                cat.setDate(resultSet.getDate(3));
                cat.setBreed(resultSet.getString(4));
                cat.setColor(CatsColor.valueOf(resultSet.getString(5)));
                Owner owner = new OwnerJdbcService().getById(resultSet.getLong(6));
                cat.setOwner(owner);
                cats.add(cat);
            }
            connection.close();
            return cats;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
