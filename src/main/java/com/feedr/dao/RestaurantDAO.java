package com.feedr.dao;

import com.feedr.models.FoodModel;
import com.feedr.models.RestaurantModel;
import com.feedr.models.UserModel;
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

    public void createRestaurant(String username, String resname, String location) throws SQLException {
        connector.executeQuery(
                String.format("INSERT INTO restaurant VALUES ('%s','%s',NULL,'%s');", username, resname, location)
        );
    }

    public void createFood(String res_username, String foodname, double price, String type) throws SQLException {
        connector.executeQuery(
                String.format("INSERT INTO food VALUES ('%s','%s',%f,'%s');", res_username, foodname, price, type)
        );
    }

    public ArrayList<RestaurantModel> getRestaurants() throws SQLException {
        ResultSet resultSet = connector.executeQuery(
                "SELECT * FROM restaurant, user;"
        );
        ArrayList<RestaurantModel> resModels = new ArrayList<>();
        while(resultSet.next()) {
            String username = resultSet.getString("username");
            String phone = resultSet.getString("phone");
            String resname = resultSet.getString("resname");
            int restaurant_rating = resultSet.getInt("restaurant_rating");
            String location = resultSet.getString("location");
            RestaurantModel resModel = new RestaurantModel(username, phone, resname, restaurant_rating, location);
            resModels.add(resModel);
        }
        return resModels;
    }

    public ArrayList<FoodModel> getRestaurantFoods(String resname) throws SQLException {
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT * FROM restaurant INNER JOIN food ON restaurant.username = food.res_username WHERE restaurant.resname = '%s';", resname)
        );
        ArrayList<FoodModel> foodModels = new ArrayList<>();
        while(resultSet.next()) {
            String res_username = resultSet.getString("res_username");
            String foodname = resultSet.getString("foodname");
            double price = resultSet.getDouble("price");
            String type = resultSet.getString("type");
            FoodModel foodModel = new FoodModel(res_username, foodname, price, type);
            foodModels.add(foodModel);
        }
        return foodModels;
    }

    public void updateFoodName(String res_username, String oldfoodname, String newfoodname) throws SQLException {
        connector.executeQuery(
                String.format("UPDATE food SET foodname = '%s' WHERE res_username = '%s' AND foodname = '%s';",
                        newfoodname, res_username, oldfoodname)
        );
    }

    public void updateFoodType(String res_username, String foodname, String newfoodtype) throws SQLException {
        connector.executeQuery(
                String.format("UPDATE food SET type = '%s' WHERE res_username = '%s' AND foodname = '%s';", res_username, foodname, newfoodtype)
        );
    }

    public void deleteFood(String res_username, String foodname) throws SQLException {
        connector.executeQuery(
                String.format("DELETE FROM food WHERE res_username = '%s' AND foodname = '%s';", res_username, foodname)
        );
    }
}
