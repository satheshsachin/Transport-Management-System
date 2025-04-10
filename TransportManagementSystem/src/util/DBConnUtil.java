package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {
    private static Connection connection;

    public static Connection getConnection(String propertyFileName) {
        try {
            String connectionString = DBPropertyUtil.getPropertyString(propertyFileName);
            if (connectionString != null) {
                connection = DriverManager.getConnection(connectionString);
            } else {
                System.out.println("Connection string is null. Check your db.properties file.");
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }
        return connection;
    }
}
