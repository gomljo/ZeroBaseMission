package com.publicwifisearch.domain.publicWifi.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnectionMaker implements DbConnectionMaker {
    private Connection dbConnection = null;
    private static final String DB_NAME="D:\\zerobase\\mission\\mission1\\publicWifiService\\publicWifi.db";
    @Override
    public Connection makeConnection() throws ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");
        try{

            dbConnection = DriverManager.getConnection("jdbc:sqlite:"+ DB_NAME);
            System.out.println(dbConnection);
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
