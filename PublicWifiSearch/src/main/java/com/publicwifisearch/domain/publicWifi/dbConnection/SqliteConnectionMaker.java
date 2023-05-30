package com.publicwifisearch.domain.publicWifi.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnectionMaker implements dbConnectionMaker{
    private Connection dbConnection = null;
    private static final String DB_NAME="/Users/ijieun/Desktop/backup/ZeroBase/sqliteTest";
    @Override
    public Connection makeConnection() {
        try{
            Class.forName("org.sqlite.jdbc4");
            dbConnection = DriverManager.getConnection("jdbc:sqlite:"+ DB_NAME);

        } catch (Exception e){
            System.out.println(e.getCause().toString());
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    @Override
    public void closeConnection() throws SQLException {
        this.dbConnection.close();
    }
}
