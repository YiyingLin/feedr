package com.feedr.dao;

import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;


public class OrderDAO {
    private DatabaseConnector connector;

    @Autowired
    public OrderDAO(DatabaseConnector connector) {
        this.connector = connector;
    }

    // Query that insert an new order without assigning the sender
    public void createOrder(String receiName, String restName, Double order_cost,
                            Double deliver_tip, Date deadline,
                            String location) throws SQLException{
        String deadLine = deadline.toString();
        connector.executeQuery(
                String.format("INSERT INTO order_info (receiver_name,restaurant_name," +
                        "order_cost,deliver_tip,order_time,deadline,delivery_location)" +
                        " VALUES ('%s','%s',%f,%f,CURRENT_TIMESTAMP,'%s','%s');", receiName,restName,order_cost,
                        deliver_tip,deadLine,location)
        );
    }
}
