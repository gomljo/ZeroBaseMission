package com.publicWifiSearch.domain.repostitory.publicWifi.wifi;

import com.publicWifiSearch.domain.repostitory.publicWifi.Repository;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.PublicWifiDetail;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.wifi.Wifi;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class WifiRepository implements Repository<Wifi> {
    private static final int BATCH_SIZE = 100;
    private static final String TABLE_NAME = "wifi";
    private static final String SAVE_QUERY = String.format("%s%s%s%s", "insert into ",TABLE_NAME," values ", "(?,?,?,?,?,?,?,?)");
    private static final String SELECT_ALL_QUERY = String.format("%s%s", "select * from", TABLE_NAME);
    private final Connection connection;
    public WifiRepository(Connection connection){
        this.connection = connection;
    }
    @Override
    public int save(HashMap<String, PublicWifiDetail> wifiDetailHashMap) {
        int result = 0 ;
        int totalData;
        int numberOfCurrentData = 0;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedQuery = connection.prepareStatement(SAVE_QUERY);
            List<String> keys = wifiDetailHashMap.keySet().stream().map(String::valueOf).collect(Collectors.toList());
            totalData = keys.size();
            for (int idx=0; idx<keys.size(); idx++){
                Wifi wifi = (Wifi) wifiDetailHashMap.get(keys.get(idx));
                preparedQuery.setObject(1, keys.get(idx));
                preparedQuery.setObject(2, wifi.getWifiName());
                preparedQuery.setBigDecimal(3, BigDecimal.valueOf(wifi.getCoordinateX()));
                preparedQuery.setBigDecimal(4, BigDecimal.valueOf(wifi.getCoordinateY()));
                preparedQuery.setObject(5, wifi.getConnectionEnvironment());
                preparedQuery.setObject(6, wifi.getNetworkType());
                preparedQuery.setObject(7, wifi.getService());
                preparedQuery.setObject(8, wifi.getDateOfWork());
                preparedQuery.addBatch();
                if(idx % BATCH_SIZE==0){
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

    }

    @Override
    public Wifi findByManagementId(String managementId) {
        return null;
    }
}
