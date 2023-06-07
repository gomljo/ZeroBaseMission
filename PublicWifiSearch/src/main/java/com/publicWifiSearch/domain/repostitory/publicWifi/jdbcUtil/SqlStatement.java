package com.publicWifiSearch.domain.repostitory.publicWifi.jdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlStatement {
    PreparedStatement makePreparedStatement(Connection connection) throws SQLException;
}
