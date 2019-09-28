package service;

import dao.UserDao;
import exception.DBException;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static UserDao getUserDao() {
        return new UserDao();
    }

    public void createTable() throws DBException {
        try {
            getUserDao().createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void cleanUp() throws DBException {
        try {
            getUserDao().dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<User> getAllUsers() throws DBException {
        try {
            return getUserDao().getAllUsers();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void addUser(String name, int age) throws DBException {
        try {
            getUserDao().addUser(name, age);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public User getUserByName(String name) throws DBException {
        try {
            return getUserDao().getUser(name);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void updateUser(User user, String name) throws DBException {
        try {
            getUserDao().updateUser(user, name);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void updateUser(User user, int age) throws DBException {
        try {
            getUserDao().updateUser(user, age);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void deleteUser(String name) throws DBException {
        try {
            getUserDao().deleteUser(name);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public long getUserIdByName(String name) throws DBException {
        try {
            return getUserDao().getClientIdByName(name);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
