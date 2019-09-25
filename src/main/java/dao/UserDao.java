package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void createTable() throws SQLException {
        Executor.execUpdate(connection, "  CREATE TABLE IF NOT EXISTS `users` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `age` VARCHAR(45) NOT NULL,\n" +
                "  PRIMARY KEY (`id`));");
    }
}
