package com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.wifi;

import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.PublicWifiDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Wifi implements PublicWifiDetail {

    private String wifiName;
    private double coordinateY;
    private double coordinateX;
    private String connectionEnvironment;
    private String networkType;
    private String service;
    private String dateOfWork;
    @Builder
    public Wifi(String wifiName, double coordinateX, double coordinateY, String connectionEnvironment, String networkType, String service, String dateOfWork) {
        this.wifiName = wifiName;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.connectionEnvironment = connectionEnvironment;
        this.networkType = networkType;
        this.service = service;
        this.dateOfWork = dateOfWork;
    }

    @Override
    public String toString() {
        return "Wifi{" +
                "wifiName='" + wifiName + '\'' +
                ", coordinateX='" + coordinateX + '\'' +
                ", coordinateY='" + coordinateY + '\'' +
                ", connectionEnvironment='" + connectionEnvironment + '\'' +
                ", networkType='" + networkType + '\'' +
                ", service='" + service + '\'' +
                ", dateOfWork='" + dateOfWork + '\'' +
                '}';
    }
}
