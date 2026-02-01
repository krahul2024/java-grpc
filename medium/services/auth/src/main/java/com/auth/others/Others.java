package com.auth.others;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Others {
    public static void testDBConn() {
        Properties props = new Properties();

        try(InputStream input = Others.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.out.println("db.properties file not found!");
                return;
            }
            props.load(input);


            String url = props.getProperty("db.url");
            String user = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            System.out.println("url = " + url + "\nuser = " + user + "\npassword = " + password);

            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn == null) {
                System.out.println("unable to connect to the db!");
                return;
            }

            System.out.println("Connected to the db");
            simpleCheck(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void simpleCheck(String url, String user, String password) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery("SELECT 1");
            if (rs.next()) {
                System.out.println("Query successful: " + rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
