package com.publicwifisearch.domain.publicWifi;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Address {
    private String district;
    private String roadAddress;
    private String detailAddress;

    public Address(String district, String roadAddress, String detailAddress) {
        this.district = district;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
    }
}
