package com.publicWifiSearch.domain.dto.openAPI.jsonParser;

import com.google.gson.*;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import com.publicWifiSearch.domain.model.publicWifi.address.Address;
import com.publicWifiSearch.domain.model.publicWifi.installation.Installation;
import com.publicWifiSearch.domain.model.publicWifi.wifi.Wifi;

import java.lang.reflect.Type;

public class PublicWifiDeserializer implements JsonDeserializer<PublicWifi> {

    private static final String MANAGEMENT_ID = "X_SWIFI_MGR_NO";
    @Override
    public PublicWifi deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject openApiData = (JsonObject) json.getAsJsonObject();
        Address address = new AddressDeserializer().deserialize(json, typeOfT, context);
        Wifi wifi = new WifiDeserializer().deserialize(json, typeOfT, context);
        Installation installation = new InstallationDeserializer().deserialize(json, typeOfT, context);


        return PublicWifi.builder()
                .managementId(openApiData.get(MANAGEMENT_ID).toString())
                .address(address)
                .wifi(wifi)
                .installation(installation)
                .build();
    }
}
