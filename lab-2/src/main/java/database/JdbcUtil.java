package database;

import java.sql.*;

public class JdbcUtil {
    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/catsservice",
                    "root", "root");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
