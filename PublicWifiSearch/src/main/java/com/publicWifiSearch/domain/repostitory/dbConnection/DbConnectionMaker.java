package com.publicWifiSearch.domain.repostitory.dbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface DbConnectionMaker {

    Connection makeConnection() throws ClassNotFoundException;
    void closeConnection() throws SQLException;

}
