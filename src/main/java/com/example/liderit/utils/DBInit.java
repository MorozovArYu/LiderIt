package com.example.liderit.utils;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public final class DBInit {
    private static final String DB_BASE_URL = "jdbc:postgresql://localhost:5432/";
    private static final String DB_NAME = "postgres";
    private static final String DB_USER = "test";
    private static final String DB_PASSWORD = "test";
    private static final String DEFAULT_QUERY = "CREATE DATABASE sport " +
            "WITH " +
            "OWNER = test " +
            "ENCODING = 'UTF8' " +
            "TABLESPACE = pg_default " +
            "CONNECTION LIMIT = -1 " +
            "IS_TEMPLATE = False;";

    public static void createSportDbIfNotExist() {
        System.out.println("Creating data base \"sport\"...");
        try (Connection connection = DriverManager.getConnection(DB_BASE_URL + DB_NAME, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(DEFAULT_QUERY);
            System.out.println("Data base created");
        } catch (SQLException ex) {
                System.out.println("Creating data base failed");
            }
        }
}
