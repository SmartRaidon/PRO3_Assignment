package dk.via.server.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance;


    private Database () throws SQLException {

    }


    public Connection getConnection () throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql");
    }

    public static Database getInstance() {
        if (instance == null) {
            try {
                instance = new Database();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }
}
