package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
//    private static String dbURL = "jdbc:postgresql://localhost:5432/university_structure";
//    private static String dbUsername = "postgres";
//    private static String dbPassword = " ";
    public static Connection getConnection() throws ClassNotFoundException {
        String dbURL = "jdbc:postgresql://localhost:5432/university_structure";
        String dbUsername = "postgres";
        String dbPassword = " ";

        FileInputStream fis;
        Properties properties = new Properties();


        Class.forName("org.postgresql.Driver");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return connection;
    }
}
