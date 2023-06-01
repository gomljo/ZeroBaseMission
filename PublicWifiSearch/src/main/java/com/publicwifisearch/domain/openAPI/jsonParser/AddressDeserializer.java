package com.publicwifisearch.domain.openAPI.jsonParser;

import com.google.gson.*;
import com.publicWifiSearch.domain.publicWifi.publicWifi.address.Address;

import java.lang.reflect.Type;

public class AddressDeserializer implements JsonDeserializer<Address> {

    @Override
    public Address deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject openApiData = (JsonObject) json.getAsJsonObject();

        return Address.builder()
                .district(openApiData.get("X_SWIFI_WRDOFC").getAsString())
                .roadAddress(openApiData.get("X_SWIFI_ADRES1").getAsString())
                .detailAddress(openApiData.get("X_SWIFI_ADRES2").getAsString())
                .build();
    }
}
