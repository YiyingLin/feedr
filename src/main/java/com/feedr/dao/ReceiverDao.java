package com.feedr.dao;

import com.feedr.models.CheckOrderModel;
import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class ReceiverDao {
    private DatabaseConnector connector;
    private Connection connection;


    @Autowired
    public ReceiverDao(DatabaseConnector connector) {
        this.connector = connector;
    }

    // Query that compute the total cost of the order
    private double computeTotalFoodCosts(int orderId) throws SQLException{
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT order_id,SUM(price* O.food_quantity) AS total_food_cost\n" +
                        "FROM order_include_food O NATURAL JOIN food\n" +
                        "WHERE order_id = %d\n" +
                        "GROUP BY order_id;", orderId)
        );
        resultSet.next();
        return resultSet.getDouble("total_food_cost");
    }


    // Receivers can confirm that their orders have been delivered
    public void confirmDelivered (int orderId) throws SQLException {
        connector.executeUpdate(
                String.format("INSERT INTO delivered(order_id, delivered_time) VALUES (%d, NOW());", orderId)
        );
        //update final tip
        ResultSet rsTip = connector.executeQuery(String.format("SELECT deliver_tip from order_info WHERE order_id=%d;", orderId));
        rsTip.next();
        double finalTip = rsTip.getDouble(1);
        connector.executeUpdate(
                String.format("UPDATE delivered SET final_tip = %f WHERE order_id = %d;", finalTip, orderId)
        );
        //update final cost
        double finalFoodCost = computeTotalFoodCosts(orderId);
        connector.executeUpdate(
                String.format("UPDATE delivered SET final_total_cost = %f WHERE order_id = %d;", finalFoodCost+finalTip, orderId)
        );
    }

    public ArrayList<CheckOrderModel> checkOrders(String receiver) throws SQLException{
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT DISTINCT o.order_id,o.order_time AS order_time,receiver_name,sender_name,restaurant_name,deliver_tip,order_cost,\n" +
                        "  deadline,delivery_location,phone,reason,\n" +
                        "  o.order_id IN (SELECT c.order_id FROM (order_info INNER JOIN cancellation c)) AS isCancelled,\n" +
                        "  o.order_id IN (SELECT d.order_id FROM (order_info INNER JOIN delivered d)) AS isDelivered\n" +
                        "FROM (receiver INNER JOIN order_info o LEFT JOIN user ON o.sender_name = user.username) LEFT JOIN\n" +
                        "cancellation ON o.order_id = cancellation.order_id\n" +
                        "WHERE o.receiver_name = '%s';", receiver)
        );
        ArrayList<CheckOrderModel> checkOrders = new ArrayList<>();

        while (resultSet.next()){
            int orderId = resultSet.getInt("order_id");
            String sender = (resultSet.getString("sender_name")==null)?"":resultSet.getString("sender_name");
            String restaurant = resultSet.getString("restaurant_name");
            double orderCost = resultSet.getDouble("order_cost");
            double tips = resultSet.getDouble("deliver_tip");
            String orderTime = resultSet.getString("order_time");
            String deadline = resultSet.getString("deadline");
            String address = resultSet.getString("delivery_location");
            String phone = (resultSet.getString("phone")==null)?"":resultSet.getString("phone");
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
