package com.publicwifisearch.domain.publicWifi.json;

import com.google.gson.*;
import com.publicwifisearch.domain.publicWifi.PublicWifi;
import com.publicwifisearch.domain.publicWifi.information.address.Address;
import com.publicwifisearch.domain.publicWifi.information.installation.Installation;
import com.publicwifisearch.domain.publicWifi.information.wifi.Wifi;

import java.lang.reflect.Type;

public class PublicWifiDeserializer implements JsonDeserializer<PublicWifi> {


    @Override
    public PublicWifi deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject openApiData = (JsonObject) json.getAsJsonObject();
        Address address = new AddressDeserializer().deserialize(json, typeOfT, context);
        Wifi wifi = new WifiDeserializer().deserialize(json, typeOfT, context);
        Installation installation = new InstallationDeserializer().deserialize(json, typeOfT, context);


        return PublicWifi.builder()
                .managementId(openApiData.get("X_SWIFI_MGR_NO").toString())
                .address(address)
                .wifi(wifi)
                .installation(installation)
                .build();
    }
}
