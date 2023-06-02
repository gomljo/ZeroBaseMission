package com.publicWifiSearch.domain.model.publicWifi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.PublicWifiDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

@Getter
@RequiredArgsConstructor
public class PublicWifiRecord{
    @SerializedName("row")
    @Expose
    private List<PublicWifi> publicWifiData;
    private static final String ADDRESS = "address";
    private static final String INSTALLATION = "installation";
    private static final String WIFI = "wifi";


    public PublicWifiRecord(List<PublicWifi> publicWifiData) {
        this.publicWifiData = publicWifiData;
    }

    public List<PublicWifi> getPublicWifiData(){
        return publicWifiData;
    }

    public Map<String, HashMap<String, PublicWifiDetail>> getPublicWifiMap(){
        Map<String, HashMap<String, PublicWifiDetail>> publicWifiMap = new HashMap<>();

        HashMap<String, PublicWifiDetail> addressHashMap = new HashMap<>();
        HashMap<String, PublicWifiDetail> installationMap = new HashMap<>();
        HashMap<String, PublicWifiDetail> wifiHashMap = new HashMap<>();
        publicWifiMap.put(ADDRESS, addressHashMap);
        publicWifiMap.put(INSTALLATION, installationMap);
        publicWifiMap.put(WIFI, wifiHashMap);

        for (PublicWifi publicWifi: publicWifiData){
            publicWifiMap.get(ADDRESS).put(publicWifi.getManagementId(), publicWifi.getAddress());
            publicWifiMap.get(INSTALLATION).put(publicWifi.getManagementId(), publicWifi.getInstallation());
            publicWifiMap.get(WIFI).put(publicWifi.getManagementId(), publicWifi.getWifi());
        }
        return Collections.unmodifiableMap(publicWifiMap);
    }

    @Override
    public String toString() {
        return "PublicWifiData{" +
                "publicWifiData=" + publicWifiData +
                '}';
    }
}

