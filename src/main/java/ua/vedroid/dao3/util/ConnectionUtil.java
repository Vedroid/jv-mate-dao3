package ua.vedroid.dao3.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    private static final String USER = "postgres";
    private static final String PASSWORD = "Postpass";
    private static final String IP = "localhost";
    private static final String PORT = "5432";
    private static final String DB = "mate";
    private static final String SCHEMA = "taxi_service";
    private static final String TIMEZONE = "UTC";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find SQL Driver", e);
        }
    }

    private ConnectionUtil() {
    }

    public static Connection getConnection() {
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", USER);
        connectionProperties.put("password", PASSWORD);
        String url = "jdbc:postgresql://" + IP + ":" + PORT + "/" + DB
                + "?currentSchema=" + SCHEMA + "&serverTimezone=" + TIMEZONE;
        try {
            return DriverManager.getConnection(url, connectionProperties);
        } catch (SQLException e) {
            throw new RuntimeException("Can`t establish the connection to DB", e);
        }
    }
}
