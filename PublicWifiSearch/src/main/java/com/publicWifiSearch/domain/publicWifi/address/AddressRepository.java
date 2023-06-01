package com.publicwifisearch.domain.publicWifi.address;

import com.publicwifisearch.domain.dbConnection.SqliteConnectionMaker;
import com.publicwifisearch.domain.publicWifi.repository;

import java.sql.Connection;

public class AddressRepository extends SqliteConnectionMaker implements repository {
    private static final String TABLE_NAME = "address";
    private static final String SAVE_QUERY = "insert into ";
    private final Connection connection;

    public AddressRepository(Connection connection){
        this.connection = connection;
    }

    @Override
    public void save() {

    }

    @Override
    public void findByManagementId() {

    }
}