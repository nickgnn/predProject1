package service;

import dao.UserDao;
import exception.DBException;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserService {
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

    private static UserDao getUserDao() {
        return new UserDao(getMysqlConnection());
    }

    public void createTable() throws DBException {
        UserDao dao = getUserDao();

        try {
            dao.createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
