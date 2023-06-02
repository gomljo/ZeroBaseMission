package com.publicWifiSearch.domain.model.publicWifi;

import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.address.Address;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.installation.Installation;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.wifi.Wifi;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

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
