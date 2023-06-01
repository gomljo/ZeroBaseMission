package com.publicWifiSearch.domain.dto.openAPI;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.publicWifiSearch.domain.dto.PublicWifiDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class OpenAPI {

    private static final Object EMPTY = null;
    private static final String FORMAT = "UTF-8";
    private static final String NUMBER_OF_TOTAL_DATA = "list_total_count";
    private URL url;
    private HttpURLConnection connection;
    private String rawJson;
    private int numberOfTotalData;
    private JsonObject json;
    private JsonObject wifiInformation;



    public OpenAPI() {

    }
    public void makeURL(URLMaker urlMaker) throws MalformedURLException {
        url = new URL(urlMaker.getURL());
    }
    public void makeHttpConnection() throws IOException{
        connection = (HttpURLConnection) url.openConnection();
    }

    public void connect() throws ProtocolException {
        this.connection.setRequestMethod("GET");
        this.connection.setRequestProperty("Content-type", "application/json");
    }
    public void checkConnectionStatus() throws IOException {
        if(!(HttpURLConnection.HTTP_OK == this.connection.getResponseCode())){
            throw new IllegalStateException();
        }
    }
    public void requestJson() throws IOException{
        StringBuilder json = new StringBuilder();

        InputStreamReader requestResult = new InputStreamReader(connection.getInputStream(), FORMAT);
        BufferedReader bufferedReader = new BufferedReader(requestResult);
        String jsonContent;
        while ((jsonContent=bufferedReader.readLine())!=EMPTY){
            json.append(jsonContent);
        }
        this.rawJson =  json.toString();
    }
    private void parseRawJson(){
        this.json = (JsonObject) JsonParser.parseString(this.rawJson);
    }
    private void parseWifiInformationFromJson(){
        this.wifiInformation =  (JsonObject) (this.json.get(UrlElement.SERVICE_TYPE.toString()));
    }
    public int getNumberOfTotalData(URLMaker urlMaker){
        try{
            makeURL(urlMaker);
            makeHttpConnection();
            connect();
            checkConnectionStatus();
            requestJson();
            parseRawJson();
            parseWifiInformationFromJson();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return this.wifiInformation.get(NUMBER_OF_TOTAL_DATA).getAsInt();
    }
    public PublicWifiDto convertToJson(){
        Gson gson = new Gson();
        JsonObject totalJson = (JsonObject) JsonParser.parseString(this.rawJson);
        JsonObject publicWifiJson = (JsonObject) totalJson.get(UrlElement.SERVICE_TYPE.toString());
        return gson.fromJson(publicWifiJson, PublicWifiDto.class);
    }

    public PublicWifiDto request(URLMaker urlMaker){
        PublicWifiDto publicWifiDto = new PublicWifiDto();
        try{
            makeURL(urlMaker);
            makeHttpConnection();
            connect();
            checkConnectionStatus();
            requestJson();
            publicWifiDto = convertToJson();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return publicWifiDto;
    }

}
