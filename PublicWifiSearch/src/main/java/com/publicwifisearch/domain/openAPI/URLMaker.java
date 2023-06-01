package com.publicwifisearch.domain.openAPI;

public class URLMaker {

    private final String startIndex;
    private final String endIndex;
    public URLMaker(int startIndex, int endIndex){
        String slash = UrlElement.SLASH.toString();
        this.startIndex = String.valueOf(startIndex) + slash;
        this.endIndex = String.valueOf(endIndex) + slash;
    }

    public String getURL(){
        return UrlElement.HOMEPAGE.toString() +
                UrlElement.PUBLIC_KEY.toString() +
                UrlElement.DATA_TYPE.toString() +
                UrlElement.SERVICE_TYPE.toString() +
                startIndex +
                endIndex;
    }

}
