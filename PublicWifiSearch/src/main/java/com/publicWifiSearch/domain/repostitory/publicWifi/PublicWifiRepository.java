package com.publicWifiSearch.domain.repostitory.publicWifi;

import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import com.publicWifiSearch.domain.repostitory.publicWifi.constant.Column;
import com.publicWifiSearch.domain.repostitory.publicWifi.jdbcUtil.JdbcLauncher;
import com.publicWifiSearch.domain.repostitory.publicWifi.jdbcUtil.ParameterHelper;
import com.publicWifiSearch.domain.repostitory.publicWifi.jdbcUtil.SqlStatement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PublicWifiRepository implements Repository<PublicWifi> {

    private static final String TABLE_NAME = "public_wifi";
    private static final String SAVE_QUERY = String.format("%s%s%s%s", "insert into ",TABLE_NAME," values ", "(?,?,?,?)");
    private static final String SELECT_ALL_QUERY = String.format("%s%s", "select * from ", TABLE_NAME);
    private static final String DELETE_ALL_QUERY = String.format("%s%s", "delete from ", TABLE_NAME);
    private static final String SELECT_BY_MANAGEMENT_ID_QUERY = String.format("%s%s%s%s", "select managementId from ", TABLE_NAME, "where managementId = ", "(?)");
    private JdbcLauncher jdbcLauncher;

    @Override
    public void connectDataBaseWith(Connection connection) {
        this.jdbcLauncher = new JdbcLauncher(connection);
    }

    @Override
    public void save(List<PublicWifi> publicWifiList) throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(SAVE_QUERY);

        ParameterHelper parameterHelper = (preparedStatement, index) -> {
            preparedStatement.setObject(Column.FIRST.getPosition(), publicWifiList.get(index).getManagementId());
            preparedStatement.setLong(Column.SECOND.getPosition(), index);
            preparedStatement.setLong(Column.THIRD.getPosition(), index);
            preparedStatement.setLong(Column.FOURTH.getPosition(), index);
        };
        this.jdbcLauncher.executeUpdateBatchWithPreparedStatement(sqlStatement, publicWifiList, parameterHelper);
    }

    @Override
    public void deleteAll() throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(DELETE_ALL_QUERY);
        this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement);
    }

    @Override
    public PublicWifi findByManagementId(String managementId) throws SQLException {
        return null;
    }

    @Override
    public List<PublicWifi> findAll() throws SQLException {
        return null;
    }
}
