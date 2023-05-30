package com.publicwifisearch.domain.publicWifi.dbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface dbConnectionMaker {

    Connection makeConnection() throws ClassNotFoundException;
    void closeConnection() throws SQLException;

}
