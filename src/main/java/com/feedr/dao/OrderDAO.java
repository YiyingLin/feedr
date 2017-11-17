package com.feedr.dao;

import com.feedr.models.FoodModel;
import com.feedr.models.OrderModel;
import com.feedr.protobuf.RestaurantProto;
import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDAO {
    private DatabaseConnector connector;

    @Autowired
    public OrderDAO(DatabaseConnector connector) {
        this.connector = connector;
    }

    // Query that insert an new order without assigning the sender
    public void createOrder(String receiName, String restName, Double order_cost,
                            Double deliver_tip, String deadline,
                            String location, List<RestaurantProto.Food> foods) throws SQLException{
        ResultSet resultSet = connector.executeAndGetAutoGeneratedKey(String.format("INSERT INTO order_info (receiver_name,restaurant_name," +
                        "order_cost,deliver_tip,order_time,deadline,delivery_location)" +
                        " VALUES ('%s','%s',%f,%f,CURRENT_TIMESTAMP,'%s','%s');", receiName,restName,order_cost,
                deliver_tip,deadline,location));
        resultSet.next();
        int autoId = resultSet.getInt(1);
        for (RestaurantProto.Food food: foods) {
            connector.executeUpdate(
                    String.format(
                            "INSERT INTO order_include_food VALUES (%d, '%s', '%s', %d);",
                            autoId, restName, food.getFoodname(), food.getQuantity()
                    )
            );
        }
    }

    public ArrayList<OrderModel> getPublicOrders() throws SQLException {
        ResultSet resultSet = connector.executeQuery(
                "SELECT * FROM order_info O, userinfo\n" +
                        "WHERE O.order_id NOT IN (\n" +
                        "SELECT order_id from deliveredorders UNION SELECT order_id from takenorders UNION SELECT order_id from cancelledorders\n" +
                        ") AND userinfo.username=O.receiver_name;"
        );
        ArrayList<OrderModel> orderModels = new ArrayList<>();
        while (resultSet.next()) {
            orderModels.add(
                    new OrderModel(
                            resultSet.getInt("order_id"),
                            resultSet.getString("receiver_name"),
                            resultSet.getString("restaurant_name"),
                            resultSet.getDouble("order_cost"),
                            resultSet.getDouble("deliver_tip"),
                            resultSet.getString("order_time"),
                            resultSet.getString("deadline"),
                            resultSet.getString("delivery_location"),
                            resultSet.getString("phone")
                    )
            );
        }
        return orderModels;
    }

    public ArrayList<FoodModel> getFoodsOfOrder(int orderId) throws SQLException {
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT *\n" +
                        "from order_include_food OF, food F\n" +
                        "WHERE order_id=%d AND OF.foodname=F.foodname AND OF.res_username=F.res_username;\n", orderId)
        );
        ArrayList<FoodModel> foodModels = new ArrayList<>();
        while (resultSet.next()) {
            foodModels.add(
                    new FoodModel(
                            resultSet.getString("F.res_username"),
                            resultSet.getString("F.foodname"),
                            resultSet.getDouble("price"),
                            resultSet.getString("type"),
                            resultSet.getInt("food_quantity")
                    )
            );
        }
        return foodModels;
    }

    public void updateTip(int order_id, double newtip) throws SQLException{
        connector.executeUpdate(
                String.format("UPDATE order_info SET delivery_tip = %f WHERE order_id = %d", newtip, order_id)
        );
    }

}
