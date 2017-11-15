package com.feedr.dao;

import com.feedr.models.CheckOrderModel;
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

    public SenderModel getSender(String username) throws SQLException{
        ResultSet resultset = connector.executeQuery(
                String.format("SELECT sender.username,sender_rating,location,phone FROM sender,user " +
                        "WHERE sender.username = user.username AND sender.username = %s;", username)
        );
        String senderName = resultset.getString("username");
        int rating = resultset.getInt("sender_rating");
        String location = resultset.getString("location");
        String phone = resultset.getString("phone");
        SenderModel sender = new SenderModel(senderName,phone,rating,location);
        return sender;
    }

    public void updateSenderRating(String username, int newRating) throws SQLException{
        connector.executeQuery(
                String.format("UPDATE sender SET sender_rating = %d WHERE username = %s;", newRating,username)
        );
    }

    public void updateLocation(String username, String location) throws SQLException{
        connector.executeQuery(
                String.format("UPDATE sender SET location = %s WHERE username = %s;", location,username)
        );
    }

    public void deleteSender(String username) throws SQLException{
        connector.executeQuery(
                String.format("DELETE FROM sender WHERE username = %s;", username)
        );
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
        // order.setAssignedSender(true);
    }

    // Check a sender's all orders whether it is cancelled or delivered
    // Return ArrayList<CheckOrderModel> that CheckOrderModel extends OrderModel
    public ArrayList<CheckOrderModel> checkOrders(String sender) throws SQLException{
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT DISTINCT o.order_id AS orderID,sender_name,receiver_name,restaurant_name,deliver_tip,order_cost,\n" +
                        "  deadline,delivery_location,phone,reason,\n" +
                        "  o.order_id IN (SELECT c.order_id FROM (order_info INNER JOIN cancellation c)) AS isCancelled,\n" +
                        "  o.order_id IN (SELECT d.order_id FROM (order_info INNER JOIN delivered d)) AS isDelivered\n" +
                        "FROM (sender INNER JOIN order_info o LEFT JOIN user ON o.sender_name = user.username) LEFT JOIN\n" +
                        "cancellation ON o.order_id = cancellation.order_id\n" +
                        "WHERE o.sender_name = '%s';", sender)
        );
        ArrayList<CheckOrderModel> checkOrders = new ArrayList<>();

        while (resultSet.next()){
            int orderId = resultSet.getInt("orderId");
            String receiver = resultSet.getString("receiver_name");
            String restaurant = resultSet.getString("restaurant_name");
            double orderCost = resultSet.getDouble("order_cost");
            double tips = resultSet.getDouble("deliver_tip");
            Timestamp orderTime = resultSet.getTimestamp("order_time");
            Timestamp deadline = resultSet.getTimestamp("deadline");
            String address = resultSet.getString("delivery_location");
            String phone = resultSet.getString("phone");
            int cancelled = resultSet.getInt("isCancelled");
            int delivered = resultSet.getInt("isDelivered");
            boolean isCancelled = (cancelled == 1);
            boolean isDelivered = (delivered == 1);
            CheckOrderModel checkOrder = new CheckOrderModel(orderId,sender,receiver,restaurant,
                    orderCost,tips,orderTime,deadline,address,phone,isCancelled,isDelivered);
            String reason = resultSet.getString("reason");
            if (reason != null){
                // if isCancelled == 1 && reason == null, the method may need to throw an error;
                checkOrder.setReason(reason);
            }
            // else checkOrder.getReason() equals to null;
            checkOrders.add(checkOrder);
        }
        return checkOrders;
    }
}