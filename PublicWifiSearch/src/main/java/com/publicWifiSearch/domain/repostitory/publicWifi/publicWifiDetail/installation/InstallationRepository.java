package com.publicWifiSearch.domain.repostitory.publicWifi.publicWifiDetail.installation;

import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.address.Address;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.installation.Installation;
import com.publicWifiSearch.domain.repostitory.publicWifi.jdbcUtil.JdbcLauncher;
import com.publicWifiSearch.domain.repostitory.publicWifi.jdbcUtil.ParameterHelper;
import com.publicWifiSearch.domain.repostitory.publicWifi.Repository;
import com.publicWifiSearch.domain.repostitory.publicWifi.jdbcUtil.SqlStatement;
import com.publicWifiSearch.domain.repostitory.publicWifi.constant.Column;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstallationRepository implements Repository<Installation> {
    private static final String TABLE_NAME = "installation";
    private static final String SAVE_QUERY = String.format("%s%s%s%s", "insert into ",TABLE_NAME," values ", "(?,?,?,?,?,?)");
    private static final String SELECT_ALL_QUERY = String.format("%s%s", "select * from ", TABLE_NAME);
    private static final String DELETE_ALL_QUERY = String.format("%s%s", "delete from ", TABLE_NAME);
    private JdbcLauncher jdbcLauncher;


    public InstallationRepository(){

    }

    @Override
    public void connectDataBaseWith(Connection connection) {
        jdbcLauncher = new JdbcLauncher(connection);
    }

    @Override
    public void save(List<Installation> installationList) throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(SAVE_QUERY);

        ParameterHelper parameterHelper = (preparedStatement, index) -> {
            preparedStatement.setLong(Column.FIRST.getPosition(), index);
            preparedStatement.setObject(Column.SECOND.getPosition(), installationList.get(index).getInstallLocation());
            preparedStatement.setObject(Column.THIRD.getPosition(), installationList.get(index).getInstallType());
            preparedStatement.setObject(Column.FOURTH.getPosition(), installationList.get(index).getInstallOffice());
            preparedStatement.setObject(Column.FIFTH.getPosition(), installationList.get(index).getInstallYear());
            preparedStatement.setObject(Column.SIXTH.getPosition(), installationList.get(index).getInstallDivision());
        };

        this.jdbcLauncher.executeUpdateBatchWithPreparedStatement(sqlStatement, installationList, parameterHelper);
    }
    public Installation toEntity(ResultSet resultSet) throws SQLException {
        return Installation.builder()
                .installLocation(resultSet.getString(Column.SECOND.getPosition()))
                .installType(resultSet.getString(Column.THIRD.getPosition()))
                .installOffice(resultSet.getString(Column.FOURTH.getPosition()))
                .installYear(resultSet.getString(Column.FIFTH.getPosition()))
                .installDivision(resultSet.getString(Column.SIXTH.getPosition()))
                .build();
    }
    @Override
    public void deleteAll() throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(DELETE_ALL_QUERY);
        this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement);
    }

    @Override
    public Installation findByManagementId(String managementId) throws SQLException {
        return null;
    }

    @Override
    public List<Installation> findAll() {
        List<Installation> installations = new ArrayList<>();

        return installations;
    }
}
