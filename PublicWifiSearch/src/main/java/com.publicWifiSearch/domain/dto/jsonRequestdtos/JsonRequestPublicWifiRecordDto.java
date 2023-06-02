package com.publicWifiSearch.domain.dto.jsonRequestdtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.publicWifiSearch.domain.dto.Dto;
import com.publicWifiSearch.domain.dto.PublicWifiDto;
import com.publicWifiSearch.domain.dto.openAPI.jsonParser.PublicWifiDeserializer;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifiRecord;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class JsonRequestPublicWifiRecordDto implements Dto<PublicWifiRecord> {

    @SerializedName("row")
    @Expose
    List<JsonRequestPublicWifiDto> publicWifiDto = new ArrayList<>();
    @Builder
    public JsonRequestPublicWifiRecordDto(List<JsonRequestPublicWifiDto> publicWifiDto) {
        this.publicWifiDto = publicWifiDto;
    }

    @Override
    public PublicWifiRecord toEntity() {
        return new PublicWifiRecord(publicWifiDto.stream().map(JsonRequestPublicWifiDto::toEntity).collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return "JsonRequestPublicWifiRecordDto{" +
                "publicWifiDto=" + publicWifiDto +
                '}';
    }
}
