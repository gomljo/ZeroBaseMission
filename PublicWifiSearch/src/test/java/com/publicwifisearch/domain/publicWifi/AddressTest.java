package com.publicwifisearch.domain.publicWifi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @DisplayName("Address 객체 생성 테스트")
    @Test
    public void 입력된_주소와_객체의_값이_다르면_false를_리턴(){
        String district = "서대문구";
        String roadAddress = "서소문로 11";
        String detailAddress = "서소문로 11 5";

        Address address = new Address(district, roadAddress, detailAddress);
        assertEquals(address.getDistrict(), district );
        assertEquals(address.getRoadAddress(), roadAddress);
        assertEquals(address.getDetailAddress(), detailAddress);
    }

}