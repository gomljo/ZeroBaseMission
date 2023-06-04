package com.publicWifiSearch.domain.repostitory.publicWifi;

import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.PublicWifiDetail;


import java.sql.SQLException;
import java.util.HashMap;

public interface Repository <T>{
    int save(HashMap<String, PublicWifiDetail> publicWifiDetailHashMap) throws SQLException;
    void deleteAll();
    T findByManagementId(String managementId) throws SQLException;
}
