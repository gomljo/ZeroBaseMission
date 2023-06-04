package com.publicWifiSearch.domain.repostitory.publicWifi.address;

import com.publicWifiSearch.domain.repostitory.dbConnection.SqliteConnectionMaker;
import com.publicWifiSearch.domain.repostitory.publicWifi.Repository;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.PublicWifiDetail;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.address.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AddressRepository extends SqliteConnectionMaker implements Repository<Address> {
    private static final String TABLE_NAME = "address";
    private static final int BATCH_SIZE = 1000;
    private static final int ZERO = 0;
    private static final int NO_REMINDER = 0;
    private static final String SAVE_QUERY = String.format("%s%s%s%s", "insert into ",TABLE_NAME," values ", "(?,?,?,?)");
    private static final String SELECT_ALL_QUERY = String.format("%s%s", "select * from", TABLE_NAME);
    private final Connection connection;

    public AddressRepository(Connection connection){
        this.connection = connection;
    }

    @Override
    public int save(HashMap<String, PublicWifiDetail> addressMap) {
        int result = ZERO;
        int totalData;
        int numberOfCurrentData = ZERO;

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedQuery = connection.prepareStatement(SAVE_QUERY);
            List<String> keys = addressMap.keySet().stream().map(String::valueOf).collect(Collectors.toList());
            totalData = keys.size();
            for (int idx=ZERO; idx<keys.size(); idx++){
                Address address = (Address) addressMap.get(keys.get(idx));
                preparedQuery.setObject(1, keys.get(idx));
                preparedQuery.setObject(2, address.getDistrict());
                preparedQuery.setObject(3, address.getRoadAddress());
                preparedQuery.setObject(4, address.getDetailAddress());

                preparedQuery.addBatch();
                if(idx % BATCH_SIZE==NO_REMINDER){
                    numberOfCurrentData = idx;
                    preparedQuery.executeBatch();
                    preparedQuery.clearBatch();
                    preparedQuery.clearParameters();
                }
            }
            if(numberOfCurrentData < totalData){
                preparedQuery.executeBatch();
                preparedQuery.clearBatch();
                preparedQuery.clearParameters();
            }
            connection.commit();
            preparedQuery.close();
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }


        return result;

    }

    @Override
    public void deleteAll() {
        int result = 0 ;
        try {
            PreparedStatement query = connection.prepareStatement("delete from "+TABLE_NAME);
            result = query.executeUpdate();
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    public Address toEntity(ResultSet resultSet) throws SQLException {
        return Address.builder()
                .district(resultSet.getString(2))
                .roadAddress(resultSet.getString(3))
                .detailAddress(resultSet.getString(4))
                .build();
    }



    @Override
    public Address findByManagementId(String managementId) throws SQLException {
        StringBuilder queryMaker = new StringBuilder();
        queryMaker.append("select ");
        queryMaker.append("managementId ");
        queryMaker.append("from ");
        queryMaker.append(TABLE_NAME);
        queryMaker.append("where ");
        queryMaker.append("managementId=");
        queryMaker.append(managementId);
        queryMaker.append(";");

        PreparedStatement query = connection.prepareStatement(queryMaker.toString());
        ResultSet resultSet = query.executeQuery();
        return toEntity(resultSet);
    }


}
