package com.publicwifisearch.domain.publicWifi;

import com.publicwifisearch.domain.publicWifi.information.address.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    String district;
    String roadAddress;
    String detailAddress;
    @BeforeEach
    public void 주소_값_선언(){
        district = "서대문구";
        roadAddress = "서소문로 11";
        detailAddress = "서소문로 11 5";
    }

    @DisplayName("Address 객체 생성 테스트")
    @Test
    public void 입력된_주소들과_객체의_값이_다르면_false를_리턴(){

        Address address = new Address(district, roadAddress, detailAddress);
        assertEquals(address.getDistrict(), district );
        assertEquals(address.getRoadAddress(), roadAddress);
        assertEquals(address.getDetailAddress(), detailAddress);
    }

    @DisplayName("Address 객체 빌더 생성 테스트")
    @Test
    public void 빌더를_이용하여_생성한_객체의_정보가_입력정보와_다르면_false를_리턴(){
        Address address1 = Address.builder()
                .district(district)
                .roadAddress(roadAddress)
                .detailAddress(detailAddress)
                .build();
        assertEquals(address1.getDistrict(), district );
        assertEquals(address1.getRoadAddress(), roadAddress);
        assertEquals(address1.getDetailAddress(), detailAddress);
    }

}