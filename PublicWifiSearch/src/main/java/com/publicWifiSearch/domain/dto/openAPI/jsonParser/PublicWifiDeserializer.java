package com.publicWifiSearch.domain.dto.openAPI.jsonParser;

import com.google.gson.*;
import com.publicWifiSearch.domain.dto.jsonRequestdtos.JsonRequestAddressDto;
import com.publicWifiSearch.domain.dto.jsonRequestdtos.JsonRequestInstallationDto;
import com.publicWifiSearch.domain.dto.jsonRequestdtos.JsonRequestPublicWifiDto;
import com.publicWifiSearch.domain.dto.jsonRequestdtos.JsonRequestWifiDto;

import java.lang.reflect.Type;

public class PublicWifiDeserializer implements JsonDeserializer<JsonRequestPublicWifiDto> {

    private static final String MANAGEMENT_ID = "X_SWIFI_MGR_NO";
    @Override
    public JsonRequestPublicWifiDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject openApiData = (JsonObject) json.getAsJsonObject();
        JsonRequestAddressDto address = new AddressDeserializer().deserialize(json, typeOfT, context);
        JsonRequestWifiDto wifi = new WifiDeserializer().deserialize(json, typeOfT, context);
        JsonRequestInstallationDto installation = new InstallationDeserializer().deserialize(json, typeOfT, context);


        return JsonRequestPublicWifiDto.builder()
                .managementId(openApiData.get(MANAGEMENT_ID).toString())
                .address(address)
                .wifi(wifi)
                .installation(installation)
                .build();
    }
}
