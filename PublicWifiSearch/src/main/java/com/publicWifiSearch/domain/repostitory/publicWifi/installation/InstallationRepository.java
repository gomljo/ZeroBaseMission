package com.publicWifiSearch.domain.repostitory.publicWifi.installation;

import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.PublicWifiDetail;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.installation.Installation;
import com.publicWifiSearch.domain.repostitory.publicWifi.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class InstallationRepository implements Repository<InstallationRepository> {
    private static final String TABLE_NAME = "installation";
    private static final int BATCH_SIZE = 100;
    private static final String SAVE_QUERY = String.format("%s%s%s%s", "insert into ",TABLE_NAME," values ", "(?,?,?,?,?,?)");
    private static final String SELECT_ALL_QUERY = String.format("%s%s", "select * from", TABLE_NAME);

    private final Connection connection;

    public InstallationRepository(Connection connection){
        this.connection = connection;
    }
    @Override
    public int save(HashMap<String, PublicWifiDetail> installationMap){
        int result = 0 ;
        int totalData;
        int numberOfCurrentData = 0;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedQuery = connection.prepareStatement(SAVE_QUERY);
            List<String> keys = installationMap.keySet().stream().map(String::valueOf).collect(Collectors.toList());
            totalData = keys.size();
            for (int idx=0; idx<keys.size(); idx++){
                Installation installation = (Installation) installationMap.get(keys.get(idx));
                preparedQuery.setObject(1, keys.get(idx));
                preparedQuery.setObject(2, installation.getInstallLocation());
                preparedQuery.setObject(3, installation.getInstallType());
                preparedQuery.setObject(4, installation.getInstallOffice());
                preparedQuery.setObject(5, installation.getInstallYear());
                preparedQuery.setObject(6, installation.getInstallDivision());

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
    public InstallationRepository findByManagementId(String managementId) throws SQLException {
        return null;
    }
}
