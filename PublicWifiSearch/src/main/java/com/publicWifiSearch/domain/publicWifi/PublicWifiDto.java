package com.publicwifisearch.domain.publicWifi;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class PublicWifiData {
    @SerializedName("row")
    @Expose
    private List<PublicWifi> publicWifiData;
    private JsonObject publicOpenApiData;
    private String openApiURL;
    public PublicWifiData(List<PublicWifi> publicWifiData) {
        this.publicWifiData = publicWifiData;
    }

    public List<PublicWifi> getPublicWifiData(){
        return publicWifiData;
    }

    @Override
    public String toString() {
        return "PublicWifiData{" +
                "publicWifiData=" + publicWifiData +
                '}';
    }
}
