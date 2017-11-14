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
                String.format("SELECT receiver.username, phone FROM receiver,user " +
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
    // Need to get the AUTO_INCREMENT KEY orderId
    public void createOrderInfo(String receiver, String restaurant, double tip, Timestamp deadline, String location) throws SQLException {
        String deadlineString = deadline.toString();
        connector.executeQuery(
                String.format("INSERT INTO order_info (receiver_name, restaurant_name, deliver_tip, deadline, delivery_location) \n" +
                        "    VALUES (%s,%s,%f,%s,%s);", receiver,restaurant,tip,deadlineString,location)
        );
    }


    // TODO: receiver order food;
    public void createOrderFood(int orderId, String restaurant, String food, int quantity) throws SQLException {
        connector.executeQuery(
                String.format("INSERT INTO order_include_food VALUES (%d,%s,%s,%d);", orderId,
                        restaurant,food,quantity)
        );
    }

    // Query that compute the cost of different foods in the order
    public void computeFoodCosts (int orderId) throws SQLException {
        connector.executeQuery(
                String.format("SELECT order_id,O.foodname,O.food_quantity,price,price* O.food_quantity AS cost " +
                        "FROM order_include_food O NATURAL LEFT JOIN food " +
                        "WHERE order_id = %d;", orderId)
        );
    }

    // Query that compute the total cost of the order
    public void computeTotalCosts (int orderId) throws SQLException{
        connector.executeQuery(
                String.format("SELECT order_id,SUM(price* O.food_quantity) AS total_cost " +
                        "FROM order_include_food O NATURAL LEFT JOIN food " +
                        "WHERE order_id = %d" +
                        "GROUP BY order_id;", orderId)
        );
    }


    // Receivers can confirm that their orders have been delivered
    public void confirmDelivered (int orderId, double finalTip, double finalCost, Timestamp deliveredTime) throws SQLException {
        String deliveredTimeString = deliveredTime.toString();
        connector.executeQuery(
                String.format("INSERT INTO delivered VALUES (%d,%f,%f,%s);", orderId, finalTip,
                        finalCost,deliveredTimeString)
        );
    }

    public ArrayList<CheckOrderModel> checkOrders(String receiver) throws SQLException{
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT DISTINCT o.order_id,receiver_name,sender_name,restaurant_name,deliver_tip,order_cost,\n" +
                        "  deadline,delivery_location,phone,reason,\n" +
                        "  o.order_id IN (SELECT c.order_id FROM (order_info INNER JOIN cancellation c)) AS isCancelled,\n" +
                        "  o.order_id IN (SELECT d.order_id FROM (order_info INNER JOIN delivered d)) AS isDelivered\n" +
                        "FROM (receiver INNER JOIN order_info o LEFT JOIN user ON o.sender_name = user.username) LEFT JOIN\n" +
                        "cancellation ON o.order_id = cancellation.order_id\n" +
                        "WHERE o.receiver_name = %s;", receiver)
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
