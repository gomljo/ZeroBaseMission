package com.publicWifiSearch.domain.repostitory.publicWifi.jdbcUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParameterHelper {

    void setParameter(PreparedStatement preparedStatement, int index) throws SQLException;

}
