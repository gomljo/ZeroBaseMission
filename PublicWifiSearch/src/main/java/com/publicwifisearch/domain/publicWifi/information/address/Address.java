package com.publicwifisearch.domain.publicWifi.information.address;


import com.google.gson.annotations.JsonAdapter;
import com.publicwifisearch.domain.publicWifi.json.AddressDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonAdapter(AddressDeserializer.class)
public class Address {

    private String district;
    private String roadAddress;
    private String detailAddress;

    @Builder
    public Address(String district, String roadAddress, String detailAddress) {
        this.district = district;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
    }

    @Override
    public String toString() {
        return "Address{" +
                "district='" + district + '\'' +
                ", roadAddress='" + roadAddress + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                '}';
    }
}
