package com.publicWifiSearch.domain.repostitory.publicWifi.publicWifiDetail.address;

import com.publicWifiSearch.domain.repostitory.dbConnection.SqliteConnectionMaker;
import com.publicWifiSearch.domain.repostitory.publicWifi.jdbcUtil.ParameterHelper;
import com.publicWifiSearch.domain.repostitory.publicWifi.jdbcUtil.JdbcLauncher;
import com.publicWifiSearch.domain.repostitory.publicWifi.Repository;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.address.Address;
import com.publicWifiSearch.domain.repostitory.publicWifi.jdbcUtil.SqlStatement;
import com.publicWifiSearch.domain.repostitory.publicWifi.constant.Column;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressRepository extends SqliteConnectionMaker implements Repository<Address> {
    private static final String TABLE_NAME = "address";
    private static final String SAVE_QUERY = String.format("%s%s%s%s", "insert into ",TABLE_NAME," values ", "(?,?,?,?)");
    private static final String SELECT_ALL_QUERY = String.format("%s%s", "select * from ", TABLE_NAME);
    private static final String DELETE_ALL_QUERY = String.format("%s%s", "delete from ", TABLE_NAME);
    private static final String SELECT_BY_MANAGEMENT_ID_QUERY = String.format("%s%s%s%s", "select managementId from ", TABLE_NAME, "where managementId = ", "(?)");
    private JdbcLauncher jdbcLauncher;
    public AddressRepository(){
    }

    @Override
    public void connectDataBaseWith(Connection connection) {
        jdbcLauncher = new JdbcLauncher(connection);
    }

    @Override
    public void save(List<Address> addressList) throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(SAVE_QUERY);

        ParameterHelper parameterHelper = (preparedStatement, index) -> {
            preparedStatement.setLong(Column.FIRST.getPosition(), index);
            preparedStatement.setObject(Column.SECOND.getPosition(), addressList.get(index).getDistrict());
            preparedStatement.setObject(Column.THIRD.getPosition(), addressList.get(index).getRoadAddress());
            preparedStatement.setObject(Column.FOURTH.getPosition(), addressList.get(index).getDetailAddress());
        };

        this.jdbcLauncher.executeUpdateBatchWithPreparedStatement(sqlStatement, addressList, parameterHelper);
    }


    @Override
    public void deleteAll() throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(DELETE_ALL_QUERY);
        this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement);
    }

    public Address toEntity(ResultSet resultSet) throws SQLException {
        return Address.builder()
                .district(resultSet.getString(Column.SECOND.getPosition()))
                .roadAddress(resultSet.getString(Column.THIRD.getPosition()))
                .detailAddress(resultSet.getString(Column.FOURTH.getPosition()))
                .build();
    }

    @Override
    public Address findByManagementId(String managementId) {
        Address address = new Address();
        SqlStatement sqlStatement = connection -> connection.prepareStatement(SELECT_BY_MANAGEMENT_ID_QUERY);
        ResultSet resultSet;
        ParameterHelper parameterHelper = (preparedStatement, index) -> preparedStatement.setObject(Column.FIRST.getPosition(), managementId);

        try {
            resultSet = jdbcLauncher.executeQueryWithPreparedStatement(sqlStatement, parameterHelper);
            address = toEntity(resultSet);
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return address;
    }

    @Override
    public List<Address> findAll() {
        List<Address> addresses = new ArrayList<>();
        SqlStatement sqlStatement = connection -> connection.prepareStatement(SELECT_ALL_QUERY);
        ResultSet resultSet;
        try {
            resultSet = jdbcLauncher.executeQueryWithPreparedStatement(sqlStatement);
            if(resultSet != null){
                while (resultSet.next()){
                    addresses.add(toEntity(resultSet));
                }
            }
        }
        catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return addresses;
    }



}
