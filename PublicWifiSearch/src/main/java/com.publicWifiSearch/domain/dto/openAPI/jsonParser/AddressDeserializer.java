package com.publicWifiSearch.domain.dto.openAPI.jsonParser;

import com.google.gson.*;
import com.publicWifiSearch.domain.dto.AddressDto;
import com.publicWifiSearch.domain.dto.jsonRequestdtos.JsonRequestAddressDto;
import com.publicWifiSearch.domain.model.publicWifi.address.Address;

import java.lang.reflect.Type;

public class AddressDeserializer implements JsonDeserializer<JsonRequestAddressDto> {

    @Override
    public JsonRequestAddressDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject openApiData = (JsonObject) json.getAsJsonObject();

        return JsonRequestAddressDto.builder()
                .district(openApiData.get("X_SWIFI_WRDOFC").getAsString())
                .roadAddress(openApiData.get("X_SWIFI_ADRES1").getAsString())
                .detailAddress(openApiData.get("X_SWIFI_ADRES2").getAsString())
                .build();
    }
}
