package com.publicWifiSearch.service;

import com.publicWifiSearch.domain.openAPI.URLMaker;
import com.publicWifiSearch.domain.openAPI.OpenAPI;
import com.publicWifiSearch.domain.publicWifi.PublicWifiDto;
import lombok.Getter;

import java.util.List;

@Getter
public class OpenAPIiService {

    public OpenAPIiService(){

    }

    public int getNumberOfTotalData(){
        URLMaker urlMaker = new URLMaker(1, 1);
        OpenAPI openAPI = new OpenAPI();
        return openAPI.getNumberOfTotalData(urlMaker);
    }

    public PublicWifiDto getPublicWifiData(int start, int end){
        URLMaker urlMaker = new URLMaker(start, end);
        OpenAPI openAPI = new OpenAPI();
        return openAPI.request(urlMaker);
    }

    public List<PublicWifiDto> requestToOpenAPI(){

    }


}
