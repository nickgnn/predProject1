package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection = getMysqlConnection();

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=1234").       //password
                    append("&serverTimezone=Europe/Moscow");

            System.out.println("URL: " + url + "\n");
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS `users` (\n" +
                     " `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                     " `name` VARCHAR(45) NOT NULL,\n" +
                     " `age` INT NOT NULL,\n" +
                     "PRIMARY KEY (`id`))";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        preparedStatement.close();
//        connection.close();
    }

    public void dropTable() throws SQLException {
        String sql = "DROP TABLE IF EXISTS `users`";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        preparedStatement.close();
//        connection.close();
    }

    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM `users`";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<User> list = new ArrayList<>();
        resultSet.last();
        long endOfList = resultSet.getRow();

        resultSet.first();

        for (int i = 1; i <= endOfList; i++) {
            list.add(new User(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age")));

            resultSet.next();
        }

        resultSet.close();
        preparedStatement.close();
//        connection.close();

        return list;
    }

    public int addUser(String name, int age) throws SQLException {
        User user = new User();
        user.setName(name);
        int rows = 0;

        if (!getAllUsers().contains(user)) {
            String sql = "INSERT INTO `users` (`name`, `age`) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);

            rows = preparedStatement.executeUpdate();

            preparedStatement.close();
//            connection.close();
        } else {
            System.out.println("This name already exists, choose another name:)");
//            connection.close();
        }

        return rows;
    }

    public User getUser(String name) throws SQLException {
        String sql = "SELECT * FROM `users` WHERE (`name` = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);

        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        User user = new User(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"));

        resultSet.close();
        preparedStatement.close();
//        connection.close();

        return user;
    }

    public void updateUser(User user, String name) throws SQLException {
        String sql = "UPDATE `users` SET `name` = ? WHERE (`id` = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setLong(2, user.getId());

        preparedStatement.execute();

        preparedStatement.close();
//        connection.close();
    }

    public void updateUser(User user, int age) throws SQLException {
        String sql = "UPDATE `users` SET `age` = ? WHERE (`id` = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, age);
        preparedStatement.setLong(2, user.getId());

        preparedStatement.execute();

        preparedStatement.close();
//        connection.close();
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
        String sql = "DELETE FROM `users` WHERE (`name` = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);

        preparedStatement.execute();
        preparedStatement.close();
//        connection.close();
    }
}
