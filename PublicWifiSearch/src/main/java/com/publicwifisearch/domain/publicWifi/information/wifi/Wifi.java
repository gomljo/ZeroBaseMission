package com.publicwifisearch.domain.publicWifi.information.wifi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.publicwifisearch.domain.publicWifi.json.WifiDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonAdapter(WifiDeserializer.class)
public class Wifi {

    private String wifiName;
    private double coordinateX;
    private double coordinateY;
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
