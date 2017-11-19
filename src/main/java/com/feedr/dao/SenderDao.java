package com.feedr.dao;

import com.feedr.models.CheckOrderModel;
import com.feedr.models.SenderModel;
import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class SenderDao {
    private DatabaseConnector connector;

    @Autowired
    public SenderDao(DatabaseConnector connector) {
        this.connector = connector;
    }

    public SenderModel getSender(String username) throws SQLException{
        ResultSet resultset = connector.executeQuery(
                String.format("SELECT S.username,sender_rating,location,phone FROM sender S,user U " +
                        "WHERE S.username = U.username AND S.username = '%s';", username)
        );
        resultset.next();
        String senderName = resultset.getString("username");
        int rating = resultset.getInt("sender_rating");
        String location = (resultset.getString("location")==null)?"":resultset.getString("location") ;
        String phone = resultset.getString("phone");
        SenderModel sender = new SenderModel(senderName,phone,rating,location);
        return sender;
    }

    // the function will assign a sender with an order, and it will also update OrderModel.senderName
    // and set assignedSender field as true
    public void takeOrder(String sender_username, int order_id) throws SQLException{
        connector.executeUpdate(
                String.format("UPDATE order_info SET sender_name = '%s' WHERE order_id = %d;",
                        sender_username, order_id)
        );
    }

    // Check a sender's all orders whether it is cancelled or delivered
    // Return ArrayList<CheckOrderModel> that CheckOrderModel extends OrderModel
    public ArrayList<CheckOrderModel> checkOrders(String sender) throws SQLException{
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT DISTINCT o.order_id AS orderID,o.order_time AS order_time,sender_name,receiver_name,restaurant_name,deliver_tip,order_cost,\n" +
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
            String orderTime = resultSet.getString("order_time");
            String deadline = resultSet.getString("deadline");
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
