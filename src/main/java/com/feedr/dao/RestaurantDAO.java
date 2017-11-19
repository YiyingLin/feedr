package com.feedr.dao;

import com.feedr.models.CheckOrderModel;
import com.feedr.models.RestaurantModel;
import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class RestaurantDAO {
    private DatabaseConnector connector;

    @Autowired
    public RestaurantDAO(DatabaseConnector connector) {
        this.connector = connector;
    }

    public void createFood(String res_username, String foodname, double price, String type) throws SQLException {
        connector.executeUpdate(
                String.format("INSERT INTO food VALUES ('%s','%s',%f,'%s');", res_username, foodname, price, type)
        );
    }

    public ArrayList<RestaurantModel> getRestaurants() throws SQLException {
        ResultSet resultSet = connector.executeQuery(
                "SELECT * FROM restaurant R, user U WHERE R.username=U.username;"
        );
        ArrayList<RestaurantModel> resModels = new ArrayList<>();
        while(resultSet.next()) {
            String username = resultSet.getString("R.username");
            String phone = resultSet.getString("phone");
            String resname = resultSet.getString("resname");
            int restaurant_rating = resultSet.getInt("restaurant_rating");
            String location = resultSet.getString("location");
            RestaurantModel resModel = new RestaurantModel(username, phone, resname, restaurant_rating, location);
            resModels.add(resModel);
        }
        return resModels;
    }

    public RestaurantModel getRestaurant(String username) throws SQLException {
        ResultSet resultSet = connector.executeQuery(
                String.format(
                        "SELECT * FROM restaurant, user WHERE user.username ='%s' AND user.username=restaurant.username;",
                        username
                )
        );
        RestaurantModel resModel = null;
        while(resultSet.next()) {
            String phone = resultSet.getString("phone");
            String resname = resultSet.getString("resname");
            int restaurant_rating = resultSet.getInt("restaurant_rating");
            String location = resultSet.getString("location");
            resModel = new RestaurantModel(username, phone, resname, restaurant_rating, location);
        }
        return resModel;
    }

    public ArrayList<CheckOrderModel> getRestaurantOrders(String resname) throws SQLException {
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT DISTINCT o.order_id,o.order_time AS order_time,receiver_name,sender_name,restaurant_name,deliver_tip,order_cost,\n" +
                        "  deadline,delivery_location,phone,reason,\n" +
                        "  o.order_id IN (SELECT c.order_id FROM (order_info INNER JOIN cancellation c)) AS isCancelled,\n" +
                        "  o.order_id IN (SELECT d.order_id FROM (order_info INNER JOIN delivered d)) AS isDelivered\n" +
                        "FROM (receiver INNER JOIN order_info o LEFT JOIN user ON o.restaurant_name = user.username) LEFT JOIN\n" +
                        "cancellation ON o.order_id = cancellation.order_id\n" +
                        "WHERE o.restaurant_name = '%s';", resname)
        );
        ArrayList<CheckOrderModel> checkOrders = new ArrayList<>();

        while (resultSet.next()) {
            int orderId = resultSet.getInt("order_id");
            String sender = (resultSet.getString("sender_name")==null)?"":resultSet.getString("sender_name");
            String receiver = resultSet.getString("receiver_name");
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

    public void deleteFood(String res_username, String foodname) throws SQLException {
        connector.executeUpdate(
                String.format("DELETE FROM food WHERE res_username = '%s' AND foodname = '%s';", res_username, foodname)
        );
    }
}
