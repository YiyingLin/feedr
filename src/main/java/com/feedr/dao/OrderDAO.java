package com.feedr.dao;

import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDAO {
    private DatabaseConnector connector;

    @Autowired
    public OrderDAO(DatabaseConnector connector) {
        this.connector = connector;
    }

    public void createOrder() {

    }
}
