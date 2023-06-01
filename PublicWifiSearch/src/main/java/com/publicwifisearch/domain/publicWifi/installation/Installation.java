package com.publicwifisearch.domain.publicWifi.installation;

import com.google.gson.annotations.JsonAdapter;
import com.publicwifisearch.domain.publicWifi.json.InstallationDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonAdapter(InstallationDeserializer.class)
public class Installation {

    private String installLocation;
    private String installType;
    private String installOffice;
    private String installYear;
    private String installDivision;

    @Builder
    public Installation(String installLocation, String installType, String installOffice, String installYear, String installDivision) {
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
}
