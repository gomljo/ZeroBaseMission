package com.publicWifiSearch.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PublicWifiDto {
    @SerializedName("row")
    @Expose
    private List<PublicWifi> publicWifiData;

    public PublicWifiDto(List<PublicWifi> publicWifiData) {
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
