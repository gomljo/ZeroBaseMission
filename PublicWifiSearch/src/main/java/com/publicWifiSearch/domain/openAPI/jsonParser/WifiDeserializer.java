package com.publicWifiSearch.domain.openAPI.jsonParser;

import com.google.gson.*;
import com.publicWifiSearch.domain.publicWifi.wifi.Wifi;

import java.lang.reflect.Type;

public class WifiDeserializer implements JsonDeserializer<Wifi> {
    @Override
    public Wifi deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject openApiData = (JsonObject) json.getAsJsonObject();
        return Wifi.builder()
                .wifiName(openApiData.get("X_SWIFI_MAIN_NM").getAsString())
                .coordinateX(openApiData.get("LAT").getAsDouble())
                .coordinateY(openApiData.get("LNT").getAsDouble())
                .connectionEnvironment("X_SWIFI_REMARS3")
                .networkType("X_SWIFI_CMCWR")
                .service("X_SWIFI_SVC_SE")
                .dateOfWork("WORK_DTTM")
                .build();
    }
}