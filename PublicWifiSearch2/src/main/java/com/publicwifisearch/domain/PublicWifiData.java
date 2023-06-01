package com.publicwifisearch.domain.publicWifi;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class PublicWifiData {
    private static final int TRANSMISSION_UNIT = 1000;
    private static final String baseUrl = "http://openapi.seoul.go.kr:8088/";
    private static final String publicKey = "616e584d52676f6d35365545766f53/";
    private static final String dataType = "json/";
    private static final String serviceType = "TbPublicWifiInfo/";
    @SerializedName("row")
    @Expose
    private List<PublicWifi> publicWifiData;
    private JsonObject publicOpenApiData;
    private String openApiURL;
    public PublicWifiData(List<PublicWifi> publicWifiData) {
        this.publicWifiData = publicWifiData;
    }

//    public void makeURL(int startIndex, int endIndex){
//
//        String urlBuilder = baseUrl +
//                publicKey +
//                dataType +
//                serviceType +
//                startIndex + "/" +
//                endIndex + "/";
//
//        this.openApiURL = urlBuilder;
//    }
//
//    public void getJson(){
//        try{
//            URL openApiUrl = new URL(this.openApiURL);
//            HttpURLConnection connection = (HttpURLConnection) openApiUrl.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Content-Type", "application/json");
//            if(connection.getResponseCode()!=200){
//                throw new IOException();
//            }
//            BufferedReader bufferedReader;
//            bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
//            String jsonContent;
//            StringBuilder json = new StringBuilder();
//            while ((jsonContent=bufferedReader.readLine())!=null){
//                json.append(jsonContent);
//            }
//            bufferedReader.close();
//            connection.disconnect();
//            publicOpenApiData = (JsonObject) JsonParser.parseString(json.toString());
//
//        } catch (IOException ioException){
//            throw new RuntimeException(ioException);
//        }
//
//    }
//
//    public void parsing(){
//        JsonObject tbWifiInfo = (JsonObject) publicOpenApiData.get("TbPublicWifiInfo");
//        JsonArray row = tbWifiInfo.get("row").getAsJsonArray();
//        Gson gson = new Gson();
//
//        for (int i = 0; i < row.size(); i+=TRANSMISSION_UNIT) {
//            Address address = gson.fromJson(row.get(i), Address.class);
//            Installation installation = gson.fromJson(row.get(i), Installation.class);
//            Wifi wifi = gson.fromJson(row.get(i), Wifi.class);
//            PublicWifi publicWifi = gson.fromJson(row.get(i), PublicWifi.class);
//            publicWifiData.add(PublicWifi.builder()
//                            .address(address)
//                            .installation(installation)
//                            .wifi(wifi)
//                            .build());
//        }
//    }

    public List<PublicWifi> getPublicWifiData(){
        return publicWifiData;
    }

    @Override
    public String toString() {
        return "PublicWifiData{" +
                "publicWifiData=" + publicWifiData +
                '}';
    }
}
