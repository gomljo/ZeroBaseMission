package com.publicWifiSearch.domain.dto.jsonRequestdtos;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.publicWifiSearch.domain.dto.AddressDto;
import com.publicWifiSearch.domain.dto.Dto;
import com.publicWifiSearch.domain.dto.openAPI.jsonParser.InstallationDeserializer;
import com.publicWifiSearch.domain.dto.openAPI.jsonParser.PublicWifiDeserializer;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import com.publicWifiSearch.domain.model.publicWifi.address.Address;
import com.publicWifiSearch.domain.model.publicWifi.installation.Installation;
import com.publicWifiSearch.domain.model.publicWifi.wifi.Wifi;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonAdapter(PublicWifiDeserializer.class)
public class JsonRequestPublicWifiDto implements Dto<PublicWifi> {
    private String managementId;

    private JsonRequestAddressDto addressDto;

    private JsonRequestInstallationDto installationDto;

    private JsonRequestWifiDto wifiDto;
    @Builder
    public JsonRequestPublicWifiDto(String managementId, JsonRequestAddressDto address, JsonRequestInstallationDto installation, JsonRequestWifiDto wifi) {
        this.addressDto = address;
        this.installationDto = installation;
        this.wifiDto = wifi;
        this.managementId = managementId;
    }


    @Override
    public PublicWifi toEntity() {
        return PublicWifi.builder()
                .managementId(managementId)
                .address(addressDto.toEntity())
                .installation(installationDto.toEntity())
                .wifi(wifiDto.toEntity())
                .build();
    }

    @Override
    public String toString() {
        return "JsonRequestPublicWifiDto{" +
                "managementId='" + managementId + '\'' +
                ", addressDto=" + addressDto +
                ", installationDto=" + installationDto +
                ", wifiDto=" + wifiDto +
                '}';
    }
}
