package com.publicWifiSearch.domain.repostitory.publicWifi;

import com.publicWifiSearch.domain.repostitory.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.dto.jsonRequestdtos.JsonRequestPublicWifiRecordDto;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.PublicWifiDetail;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifiRecord;
import com.publicWifiSearch.domain.repostitory.publicWifi.address.AddressRepository;
import com.publicWifiSearch.domain.repostitory.publicWifi.installation.InstallationRepository;
import com.publicWifiSearch.domain.repostitory.publicWifi.wifi.WifiRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicWifiRepository {
    private final DbConnectionMaker dbConnectionMaker;
    private List<PublicWifi> publicWifiTotal = new ArrayList<>();

    public PublicWifiRepository(DbConnectionMaker dbConnectionMaker){
        this.dbConnectionMaker = dbConnectionMaker;
    }

    public int save(List<JsonRequestPublicWifiRecordDto> jsonRequestPublicWifiRecordDto) {
        Connection connection = dbConnectionMaker.makeConnection();
        AddressRepository addressRepository = new AddressRepository(connection);
        InstallationRepository installationRepository = new InstallationRepository(connection);
        WifiRepository wifiRepository = new WifiRepository(connection);

        for (JsonRequestPublicWifiRecordDto jsonRequestDto: jsonRequestPublicWifiRecordDto) {
            PublicWifiRecord publicWifiRecord = jsonRequestDto.toEntity();
            publicWifiTotal.addAll(publicWifiRecord.getPublicWifiData());
        }
        System.out.println(publicWifiTotal.size());
        PublicWifiRecord publicWifiRecord = new PublicWifiRecord(publicWifiTotal);
        Map<String, HashMap<String, PublicWifiDetail>> publicWifiTotalMap = publicWifiRecord.getPublicWifiMap();

        addressRepository.save(publicWifiTotalMap.get("address"));
        installationRepository.save(publicWifiTotalMap.get("installation"));
        wifiRepository.save(publicWifiTotalMap.get("wifi"));

        dbConnectionMaker.closeConnection();
        return 0;
    }

    public int getNumberOfData(){
        return publicWifiTotal.size();
    }

    public PublicWifiRecord findByManagementId(String managementId) throws SQLException {
        return null;
    }
}
