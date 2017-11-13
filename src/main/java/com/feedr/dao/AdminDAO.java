package com.feedr.dao;

import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminDAO {
    private DatabaseConnector connector;

    @Autowired
    public AdminDAO(DatabaseConnector connector) {
        this.connector = connector;
    }




}
