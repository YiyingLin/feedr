package com.feedr.dao;

import com.feedr.models.CheckOrderModel;
import com.feedr.models.ReceiverModel;
import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ReceiverDao {
    private DatabaseConnector connector;

    @Autowired
    public ReceiverDao(DatabaseConnector connector) {
        this.connector = connector;
    }

    public void createReceiver(String username, int senderRating, String location) throws SQLException {
        connector.executeQuery(
                String.format("INSERT INTO receiver values (%s);", username)
        );
    }

    public ArrayList<ReceiverModel> getReceivers() throws SQLException{
        ResultSet resultset = connector.executeQuery(
                String.format("SELECT receiver.username, phone FROM receiver,user " +
                        "WHERE receiver.username = user.username;")
        );
        ArrayList<ReceiverModel> receivers = new ArrayList<>();
        while(resultset.next()){
            String username = resultset.getString("username");
            String phone = resultset.getString("phone");
            ReceiverModel receiver = new ReceiverModel(username,phone);
            receivers.add(receiver);
        }
        return receivers;
    }

    public ReceiverModel getReceiver(String username) throws SQLException {
        ResultSet resultset = connector.executeQuery(
                String.format("SELECT receiver.username, phone FROM receiver,user" +
                        "WHERE receiver.username = user.username AND receiver.username = %s;", username)
        );
        String receiverName = resultset.getString("username");
        String phone = resultset.getString("phone");
        ReceiverModel receiver = new ReceiverModel(receiverName, phone);
        return receiver;
    }

    public void deleteReceiver(String username) throws SQLException {
        connector.executeQuery(
                String.format("DELETE FROM receiver WHERE username = %s;", username)
        );
    }

    // TODO: receiver creates order
    public void createOrderInfo(String receiver, String restaurant, double tip, Timestamp deadline, String location) throws SQLException {
        String deadlineString = deadline.toString();
        connector.executeQuery(
                String.format("INSERT INTO order_info (receiver_name, restaurant_name, deliver_tip, deadline, delivery_location) \n" +
                        "    VALUES (%s,%s,%d,%s,%s);", receiver,restaurant,tip,deadlineString,location)
        );
    }


    // TODO: receiver order food;
    public void createOrderFood(int orderId, String restaurant, String food, int quantity) throws SQLException {
        connector.executeQuery(
                String.format("INSERT INTO order_include_food VALUES (%d,%s,%s,%d);", orderId,
                        restaurant,food,quantity)
        );
    }


    public ArrayList<CheckOrderModel> checkOrders(String receiver) throws SQLException{
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT DISTINCT order_id,receiver_name,sender_name,restaurant_name,deliver_tip,order_cost,\n" +
                        "  deadline,delivery_location,phone,\n" +
                        "  order_id IN (SELECT c.order_id FROM (order_info INNER JOIN cancellation c)) AS isCancelled,\n" +
                        "  order_id IN (SELECT d.order_id FROM (order_info INNER JOIN delivered d)) AS isDelivered\n" +
                        "FROM receiver INNER JOIN order_info LEFT JOIN user ON order_info.sender_name = user.username\n" +
                        "WHERE order_info.receiver_name = '%s';", receiver)
        );
        ArrayList<CheckOrderModel> checkOrders = new ArrayList<>();

        while (resultSet.next()){
            int orderId = resultSet.getInt("order_id");
            String sender = resultSet.getString("sender_name");
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
            checkOrders.add(checkOrder);
        }
        return checkOrders;
    }

}
