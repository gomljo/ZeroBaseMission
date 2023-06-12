package com.publicWifiSearch.domain.dto.openAPI.jsonParser.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifiRecord;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.address.Address;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.installation.Installation;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.wifi.Wifi;

import java.lang.reflect.Type;
import java.util.List;

public class PublicWifiSerializer implements JsonSerializer<PublicWifi> {
    @Override
    public JsonElement serialize(PublicWifi publicWifi, Type typeOfSrc, JsonSerializationContext context) {



        return null;
    }
}
