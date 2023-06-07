package com.publicWifiSearch.domain.repostitory.publicWifi.constant;

public enum Column {

    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5),
    SIXTH(6),
    SEVENTH(7),
    EIGHTH(8);

    private int position;

    Column(int position){
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
