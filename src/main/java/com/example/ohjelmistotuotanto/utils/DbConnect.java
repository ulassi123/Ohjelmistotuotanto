package com.example.ohjelmistotuotanto.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Luokka SQL-tietokantaan yhdistämisteen
 */
public class DbConnect {
    private final String DB_URL = "";
    private final String DB_NAME = "";
    private final String DB_PASSWORD = "";

    public Connection yhdista() {
        try{
            return DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public String getDB_NAME() {
        return DB_NAME;
    }

    public String getDB_PASSWORD() {
        return DB_PASSWORD;
    }

}

