package repository.jdbc;

import dao.OwnerDao;
import database.JdbcUtil;
import entities.Cat;
import entities.Owner;
import entities.enums.CatsColor;
import java.sql.*;
import java.util.ArrayList;

public class OwnerJdbcService implements OwnerDao {

    public Owner save(Owner owner) {
        Connection connection;
        try {
            connection = JdbcUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO owners (OwnerId, Name, Date) VALUES(?, ?, ?)");
            preparedStatement.setLong(1, owner.getId());
            preparedStatement.setString(2, owner.getName());
            preparedStatement.setDate(3, new java.sql.Date(owner.getDate().getTime()));
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return owner;
    }

    public void deleteById(long id) {
        Connection connection;
        try {
            connection = JdbcUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM cats WHERE OwnerId = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM owners WHERE OwnerId = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByEntity(Owner owner) {
        Connection connection;
        try {
            connection = JdbcUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM cats WHERE OwnerId = ?");
            preparedStatement.setLong(1, owner.getId());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM owners WHERE OwnerId = ?");
            preparedStatement.setLong(1, owner.getId());
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
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM owners");
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Owner update(Owner owner) {
        Connection connection;
        try {
            connection = JdbcUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE owners SET Name = ?, Date = ? WHERE OwnerId = ?");
            preparedStatement.setString(1, owner.getName());
            preparedStatement.setDate(2, (Date) owner.getDate());
            preparedStatement.setLong(3, owner.getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return owner;
    }

    public Owner getById(long id) {
        Connection connection;
        try {
            connection = JdbcUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM owners WHERE OwnerId = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Owner owner = new Owner();
            resultSet.next();
            owner.setId(resultSet.getLong(1));
            owner.setName(resultSet.getString(2));
            owner.setDate(resultSet.getDate(3));
            connection.close();
            return owner;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Owner> getAll() {
        Connection connection;
        try {
            connection = JdbcUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM owners");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Owner> owners = new ArrayList<Owner>();
            Owner owner;
            while (resultSet.next()) {
                owner = new Owner();
                owner.setId(resultSet.getLong(1));
                owner.setName(resultSet.getString(2));
                owner.setDate(resultSet.getDate(3));
                owners.add(owner);
            }
            connection.close();
            return owners;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Cat> getAllByOwnerId(long id) {
        Connection connection;
        try {
            connection = JdbcUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM cats WHERE OwnerId = ?");
            preparedStatement.setLong(1, id);
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
                Owner owner = getById(id);
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
