package com.publicWifiSearch.domain.repostitory.publicWifi;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository <T>{
    void connectDataBaseWith(Connection connection);
    void save(List<T> detailTypeList) throws SQLException;
    void deleteAll() throws SQLException;
    T findByManagementId(String managementId) throws SQLException;

    List<T> findAll() throws SQLException;

}
