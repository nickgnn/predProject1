package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
    public static long execUpdate(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
        long updated = statement.getUpdateCount();
        statement.close();

        return updated;
    }

    public static <T> T execQuery(Connection connection, String query, ResultHandler<T> handler) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);

        ResultSet resultSet = statement.getResultSet();
        T value = handler.handle(resultSet);

        resultSet.close();
        statement.close();

        return value;
    }
}
