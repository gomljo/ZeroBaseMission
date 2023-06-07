package com.publicWifiSearch.domain.repostitory.publicWifi.wifi;

import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.address.Address;
import com.publicWifiSearch.domain.repostitory.publicWifi.JdbcLauncher;
import com.publicWifiSearch.domain.repostitory.publicWifi.ParameterHelper;
import com.publicWifiSearch.domain.repostitory.publicWifi.Repository;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.PublicWifiDetail;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.wifi.Wifi;
import com.publicWifiSearch.domain.repostitory.publicWifi.SqlStatement;
import com.publicWifiSearch.domain.repostitory.publicWifi.constant.Column;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class WifiRepository implements Repository<Wifi> {
    private static final int BATCH_SIZE = 100;
    private static final String TABLE_NAME = "wifi";
    private static final String SAVE_QUERY = String.format("%s%s%s%s", "insert into ",TABLE_NAME," values ", "(?,?,?,?,?,?,?,?)");
    private static final String SELECT_ALL_QUERY = String.format("%s%s", "select * from ", TABLE_NAME);
    private JdbcLauncher jdbcLauncher;
    private Connection connection;
    public WifiRepository(){

    }

    @Override
    public void connectDataBaseWith(Connection connection) {
        this.connection = connection;
        jdbcLauncher = new JdbcLauncher(this.connection, Address.class.getName());
    }
    @Override
    public void save(List<Wifi> wifiList) throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(SAVE_QUERY);

        ParameterHelper parameterHelper = (preparedStatement, index) -> {
            preparedStatement.setObject(Column.FIRST.getPosition(), index);
            preparedStatement.setObject(Column.SECOND.getPosition(), wifiList.get(index).getWifiName());
            preparedStatement.setObject(Column.THIRD.getPosition(), wifiList.get(index).getCoordinateX());
            preparedStatement.setObject(Column.FOURTH.getPosition(), wifiList.get(index).getCoordinateY());
            preparedStatement.setObject(Column.FIFTH.getPosition(), wifiList.get(index).getConnectionEnvironment());
            preparedStatement.setObject(Column.SIXTH.getPosition(), wifiList.get(index).getNetworkType());
            preparedStatement.setObject(Column.SEVENTH.getPosition(), wifiList.get(index).getService());
            preparedStatement.setObject(Column.EIGHTH.getPosition(), wifiList.get(index).getDateOfWork());
        };

        this.jdbcLauncher.executeUpdateBatchWithPreparedStatement(sqlStatement, wifiList, parameterHelper);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Wifi findByManagementId(String managementId) {
        return null;
    }
    public Wifi toEntity(ResultSet resultSet) throws SQLException {
        return Wifi.builder()
                .wifiName(resultSet.getString(Column.SECOND.getPosition()))
                .coordinateX(resultSet.getDouble(Column.THIRD.getPosition()))
                .coordinateY(resultSet.getDouble(Column.FOURTH.getPosition()))
                .connectionEnvironment(resultSet.getString(Column.FIFTH.getPosition()))
                .networkType(resultSet.getString(Column.SIXTH.getPosition()))
                .service(resultSet.getString(Column.SEVENTH.getPosition()))
                .dateOfWork(resultSet.getString(Column.EIGHTH.getPosition()))
                .build();
    }
    @Override
    public List<Wifi> findAll() {
        List<Wifi> wifis = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                wifis.add(toEntity(resultSet));
            }
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return wifis;
    }
}
