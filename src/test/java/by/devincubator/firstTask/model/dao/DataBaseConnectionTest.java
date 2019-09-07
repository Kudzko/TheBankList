package by.devincubator.firstTask.model.dao;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class DataBaseConnectionTest {
    static final String DRIVER_NAME = DBConfig.DRIVER;
    static final String DB_URL = DBConfig.DB_URL;
    static final String DB_USER = DBConfig.USER_DB;
    static final String PASSWORD = DBConfig.PASS_DB;


    @Test
    public void connectionToDBtest() throws SQLException {

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)) {

            assertTrue(con.isValid(1));
        }
    }

    @Test
    public void connectionPoolDBtest() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;

        try {
            for (int i = 0; i < DBConfig.AMOUNT_CONNECTIONS; i++) {
                connection = pool.getConnection();
                assertTrue(connection.isValid(1));
            }
        } finally {
            pool.closeAllConnections();
        }


    }
}
