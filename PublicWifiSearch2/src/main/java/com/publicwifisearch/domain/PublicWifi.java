package com.publicwifisearch.domain.publicWifi;

import com.google.gson.annotations.JsonAdapter;
import com.publicwifisearch.domain.publicWifi.publicWifi.address.Address;
import com.publicwifisearch.domain.publicWifi.publicWifi.installation.Installation;
import com.publicwifisearch.domain.publicWifi.publicWifi.wifi.Wifi;
import com.publicwifisearch.domain.publicWifi.json.PublicWifiDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonAdapter(PublicWifiDeserializer.class)
public class PublicWifi {

    private String managementId;

    private Address address;

    private Installation installation;

    private Wifi wifi;
    @Builder
    public PublicWifi(String managementId, Address address, Installation installation, Wifi wifi) {
        this.address = address;
        this.installation = installation;
        this.wifi = wifi;
        this.managementId = managementId;
    }


    @Override
    public String toString() {
        return "PublicWifi{" +
                "managementId='" + managementId + '\'' +
                ", address=" + address +
                ", installation=" + installation +
                ", wifi=" + wifi +
                '}';
    }
}
