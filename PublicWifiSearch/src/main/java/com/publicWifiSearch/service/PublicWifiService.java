package com.publicWifiSearch.service;

import com.publicWifiSearch.domain.dto.jsonRequestdtos.JsonRequestPublicWifiRecordDto;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifiRecord;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.PublicWifiDetail;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.address.Address;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.installation.Installation;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.wifi.Wifi;
import com.publicWifiSearch.domain.repostitory.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.publicWifi.PublicWifiRepository;
import com.publicWifiSearch.domain.repostitory.publicWifi.Repository;
import com.publicWifiSearch.domain.repostitory.publicWifi.publicWifiDetail.address.AddressRepository;
import com.publicWifiSearch.domain.repostitory.publicWifi.publicWifiDetail.installation.InstallationRepository;
import com.publicWifiSearch.domain.repostitory.publicWifi.publicWifiDetail.wifi.WifiRepository;
//import com.publicWifiSearch.domain.repostitory.publicWifi.address.AddressRepository;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;


public class PublicWifiService {

    private final DbConnectionMaker dbConnectionMaker;
    private Connection connection;
    private final List<PublicWifi> publicWifiTotal = new ArrayList<>();
    private List<Address> addressList;
    private List<Installation> installationList;
    private List<Wifi> wifiList;
    public PublicWifiService(DbConnectionMaker dbConnectionMaker) {
        this.dbConnectionMaker = dbConnectionMaker;
    }

    private void collectOpenApiRequest(List<JsonRequestPublicWifiRecordDto> openApiRequestDto){
        for (JsonRequestPublicWifiRecordDto jsonRequestDto: openApiRequestDto) {
            PublicWifiRecord publicWifiRecord = jsonRequestDto.toEntity();
            publicWifiTotal.addAll(publicWifiRecord.getPublicWifiData());
        }
    }
    private void splitOpenApiRequest(){
        this.addressList = new ArrayList<>();
        this.installationList = new ArrayList<>();
        this.wifiList = new ArrayList<>();

        for (PublicWifi publicWifi:this.publicWifiTotal){
            addressList.add(publicWifi.getAddress());
            installationList.add(publicWifi.getInstallation());
            wifiList.add(publicWifi.getWifi());
        }

    }
    private void makeConnection(){
        this.connection = dbConnectionMaker.makeConnection();
    }
    private void closeConnection(){
        dbConnectionMaker.closeConnection();
    }

    private <PublicWifiType> void saveEachRepository(Repository<PublicWifiType> repository, List<PublicWifiType> eachRequestResult){
        try {
            makeConnection();
            if(this.connection!=null){
                repository.connectDataBaseWith(this.connection);
            }
            repository.save(eachRequestResult);
            closeConnection();
        }
        catch (NullPointerException | SQLException exception){
            throw new RuntimeException(exception);
        }
    }

    private <PublicWifiType> void deleteEachRepository(Repository<PublicWifiType> repository){
        try {
            makeConnection();
            if(this.connection!=null){
                repository.connectDataBaseWith(this.connection);
            }
            repository.deleteAll();
            closeConnection();
        }
        catch (NullPointerException | SQLException exception){
            throw new RuntimeException(exception);
        }
    }

    public int getNumberOfData(){
        return publicWifiTotal.size();
    }

    public void saveOpenApiRequest(List<JsonRequestPublicWifiRecordDto> openApiRequestDto){
        AddressRepository addressRepository = new AddressRepository();
        InstallationRepository installationRepository = new InstallationRepository();
        WifiRepository wifiRepository = new WifiRepository();
        PublicWifiRepository publicWifiRepository = new PublicWifiRepository();

        try {
            collectOpenApiRequest(openApiRequestDto);
            splitOpenApiRequest();
            saveEachRepository(addressRepository, addressList);
            saveEachRepository(installationRepository, installationList);
            saveEachRepository(wifiRepository, wifiList);
            saveEachRepository(publicWifiRepository, publicWifiTotal);
        }
        catch (NullPointerException nullPointerException){
            throw new RuntimeException(nullPointerException);
        }
    }

    public void deleteOpenApiRequest(){
        AddressRepository addressRepository = new AddressRepository();
        InstallationRepository installationRepository = new InstallationRepository();
        WifiRepository wifiRepository = new WifiRepository();
        PublicWifiRepository publicWifiRepository = new PublicWifiRepository();
        try {
            deleteEachRepository(addressRepository);
            deleteEachRepository(installationRepository);
            deleteEachRepository(wifiRepository);
            deleteEachRepository(publicWifiRepository);
        }
        catch (NullPointerException nullPointerException){
            throw new RuntimeException(nullPointerException);
        }
    }

    public PublicWifiRecord findByManagementId(String managementId) {
        return null;
    }

//    public PublicWifiRecord findAll(){
//        PublicWifiRecord publicWifiRecord;
//        connectDatabaseEach();
//    }
//
//    public List<PublicWifi> findNearestTwentyWifi(){
//
//    }

}
