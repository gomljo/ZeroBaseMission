package com.publicWifiSearch.domain.dto.openAPI.jsonParser;

import com.publicWifiSearch.domain.dto.openAPIRequestdtos.OpenApiRequestWifiDto;
import com.google.gson.*;

import java.lang.reflect.Type;

public class WifiDeserializer implements JsonDeserializer<OpenApiRequestWifiDto> {
    @Override
    public OpenApiRequestWifiDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject openApiData = (JsonObject) json.getAsJsonObject();
        return OpenApiRequestWifiDto.builder()
                .wifiName(openApiData.get("X_SWIFI_MAIN_NM").getAsString())
                .coordinateX(openApiData.get("LNT").getAsDouble())
                .coordinateY(openApiData.get("LAT").getAsDouble())
                .connectionEnvironment(openApiData.get("X_SWIFI_REMARS3").getAsString())
                .networkType(openApiData.get("X_SWIFI_CMCWR").getAsString())
                .service(openApiData.get("X_SWIFI_SVC_SE").getAsString())
                .dateOfWork(openApiData.get("WORK_DTTM").getAsString())
                .build();
    }
}