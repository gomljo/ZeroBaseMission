package com.publicWifiSearch.domain.dto.jsonRequestdtos;

import com.google.gson.annotations.JsonAdapter;

import com.publicWifiSearch.domain.dto.Dto;
import com.publicWifiSearch.domain.dto.openAPI.jsonParser.InstallationDeserializer;
import com.publicWifiSearch.domain.model.publicWifi.installation.Installation;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonAdapter(InstallationDeserializer.class)
public class JsonRequestInstallationDto implements Dto<Installation> {

    private String installLocation;
    private String installType;
    private String installOffice;
    private String installYear;
    private String installDivision;

    @Builder
    public JsonRequestInstallationDto(String installLocation, String installType, String installOffice, String installYear, String installDivision) {
        this.installLocation = installLocation;
        this.installType = installType;
        this.installOffice = installOffice;
        this.installYear = installYear;
        this.installDivision = installDivision;
    }

    @Override
    public String toString() {
        return "Installation{" +
                "installLocation='" + installLocation + '\'' +
                ", installType='" + installType + '\'' +
                ", installOffice='" + installOffice + '\'' +
                ", installYear='" + installYear + '\'' +
                ", installDivision='" + installDivision + '\'' +
                '}';
    }

    @Override
    public Installation toEntity() {
        return Installation.builder()
                .installLocation(installLocation)
                .installType(installType)
                .installOffice(installOffice)
                .installYear(installYear)
                .installDivision(installDivision)
                .build();
    }
}
