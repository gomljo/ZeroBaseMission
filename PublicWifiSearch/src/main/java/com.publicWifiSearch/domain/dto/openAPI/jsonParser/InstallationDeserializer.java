package com.publicWifiSearch.domain.dto.openAPI.jsonParser;

import com.google.gson.*;
import com.publicWifiSearch.domain.dto.jsonRequestdtos.JsonRequestInstallationDto;
import com.publicWifiSearch.domain.model.publicWifi.installation.Installation;

import java.lang.reflect.Type;

public class InstallationDeserializer implements JsonDeserializer<JsonRequestInstallationDto> {
    @Override
    public JsonRequestInstallationDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject openApiData = (JsonObject) json.getAsJsonObject();

        return JsonRequestInstallationDto.builder()
                .installLocation(openApiData.get("X_SWIFI_INSTL_FLOOR").isJsonNull() ? "null": openApiData.get("X_SWIFI_INSTL_FLOOR").getAsString())
                .installType(openApiData.get("X_SWIFI_INSTL_TY").getAsString())
                .installOffice(openApiData.get("X_SWIFI_INSTL_MBY").getAsString())
                .installYear(openApiData.get("X_SWIFI_CNSTC_YEAR").getAsString())
                .installDivision(openApiData.get("X_SWIFI_INOUT_DOOR").getAsString())
                .build();
    }
}