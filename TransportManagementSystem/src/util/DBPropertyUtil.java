package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static String getPropertyString(String fileName) {
        Properties props = new Properties();

        try {
            FileInputStream fis = new FileInputStream(fileName); // Reads db.properties
            props.load(fis);
        } catch (IOException e) {
            System.out.println("Error reading db.properties file");
            e.printStackTrace();
        }

        String host = props.getProperty("hostname");
        String port = props.getProperty("port");
        String db = props.getProperty("dbname");
        String user = props.getProperty("username");
        String pass = props.getProperty("password");

        if (host == null || port == null || db == null || user == null || pass == null) {
            System.out.println("Missing properties in db.properties file.");
            return null;
        }

        return "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user + "&password=" + pass;
    }
}
