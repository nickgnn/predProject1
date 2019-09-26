package dao;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void createTable() throws SQLException {
        Executor.execUpdate(connection, "CREATE TABLE IF NOT EXISTS `users` (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`));");
    }

    public void dropTable() throws SQLException {
        Executor.execUpdate(connection, "DROP TABLE IF EXISTS `users`");
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = Executor.execQuery(connection, "SELECT * FROM `users`", resultSet -> {
            List<User> list1 = new ArrayList<>();
            resultSet.last();
            long endOfList = resultSet.getRow();

            resultSet.first();

            for (int i = 1; i <= endOfList; i++) {
                list1.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")));

                resultSet.next();
            }

            return list1;
        });

        return list;
    }

    public void addUser(String name, int age) throws SQLException {
        User user = new User();
        user.setName(name);

        if (!getAllUsers().contains(user)) {
            Executor.execUpdate(connection, "INSERT INTO `users` (`name`, `age`)" +
                    " VALUES ('" + name + "', '" + age + "');\n");
        }
    }

    public User getUser(String name) throws SQLException {
        return Executor.execQuery(connection, "SELECT * FROM `users` WHERE (`name` = '" + name + "')", resultSet -> {
            resultSet.next();
            return new User(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"));
        });
    }

    public void updateUser(User user, String name) throws SQLException {
        Executor.execUpdate(connection, "UPDATE `users` SET `name` = '" + name + "' WHERE (`id` = '" + user.getId() + "')");
    }

    public void updateUser(User user, int age) throws SQLException {
        Executor.execUpdate(connection, "UPDATE `users` SET `age` = '" + age + "' WHERE (`id` = '" + user.getId() + "')");
    }

    public long getClientIdByName(String name) throws SQLException {
        long id = 0;

        for (User user : getAllUsers()) {
            if (user.getName().equals(name)) {
                id = user.getId();
            }
        }

        return id;
    }

    public void deleteUser(String name) throws SQLException {
        Executor.execUpdate(connection, "DELETE FROM `users` WHERE (`name` = '" + name + "')");
    }
}
