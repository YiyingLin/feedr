package com.feedr.dao;

import com.feedr.models.SenderModel;
import com.feedr.models.OrderModel;
import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class SenderDao {
    private DatabaseConnector connector;

    @Autowired
    public SenderDao(DatabaseConnector connector) {
        this.connector = connector;
    }

    public void createSender(String username, int senderRating, String location) throws SQLException{
        connector.executeQuery(
                String.format("INSERT INTO sender values (%s,%d,%s);", username, senderRating, location)
        );
    }

    public ArrayList<SenderModel> getSenders() throws SQLException{
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT sender.username,sender_rating,location,phone FROM sender,user " +
                        "WHERE sender.username = user.username;")
        );
        ArrayList<SenderModel> senders = new ArrayList<>();
        while(resultSet.next()){
            String username = resultSet.getString("username");
            String phone = resultSet.getString("phone");
            int rating = resultSet.getInt("sender_rating");
            String location = resultSet.getString("location");
            SenderModel sender = new SenderModel(username,phone,rating,location);
            senders.add(sender);
        }
        return senders;
    }

    // the function will assign a sender with an order, and it will also update OrderModel.senderName
    // and set assignedSender field as true
    public void takeOrder(SenderModel sender, OrderModel order) throws SQLException{
        String senderName = sender.getUsername();
        int orderId = order.getOrderID();
        connector.executeQuery(
                String.format("UPDATE order_info SET sender_name = %s WHERE order_id = %d;",
                        senderName, orderId)
        );
        order.setSender(senderName);
        order.setAssignedSender(true);
    }

    public ArrayList<OrderModel> checkOrders(SenderModel sender) throws SQLException{
        String senderName = sender.getUsername();
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT * FROM order_info WHERE sender_name = %s;", senderName)
        );
        ArrayList<OrderModel> senderOrders = new ArrayList<>();

        while (resultSet.next()){
            int orderId = resultSet.getInt("order_id");
            String receiver = resultSet.getString("receiver_name");
            String restaurant = resultSet.getString("restaurant_name");
            double orderCost = resultSet.getDouble("order_cost");
            double tips = resultSet.getDouble("deliver_tip");
            Timestamp orderTime = resultSet.getTimestamp("order_time");
            Timestamp deadline = resultSet.getTimestamp("deadline");
            String address = resultSet.getString("delivery_location");

        }
        return senderOrders;
    }





}
