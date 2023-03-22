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
    private static final String CREATE_USER_QUERY = "CREATE ROLE test WITH\n" +
            "    LOGIN\n" +
            "    SUPERUSER\n" +
            "    CREATEDB\n" +
            "    CREATEROLE\n" +
            "    INHERIT\n" +
            "    NOREPLICATION\n" +
            "    CONNECTION LIMIT -1\n" +
            "    PASSWORD 'test';";
    private static final String CREATE_DB_QUERY = "CREATE DATABASE sports " +
            "WITH " +
            "OWNER = test " +
            "ENCODING = 'UTF8' " +
            "TABLESPACE = pg_default " +
            "CONNECTION LIMIT = -1 " +
            "IS_TEMPLATE = False;";


    public static void initSportsDbIfNotExist(String db_user, String db_password) throws SQLException {
        System.out.println("Creating data base \"sports\"...");
        try (Connection connection = DriverManager.getConnection(DB_BASE_URL + DB_NAME, db_user, db_password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_USER_QUERY);
            statement.executeUpdate(CREATE_DB_QUERY);
            System.out.println("Data base initialized");
        } catch (SQLException ex) {
            System.out.println("Creating data base failed");
            throw ex;
        }
    }
}