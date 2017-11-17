package com.feedr.dao;

import com.feedr.models.FoodModel;
import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class FoodDAO {
    private DatabaseConnector connector;

    @Autowired
    public FoodDAO(DatabaseConnector connector) {
        this.connector = connector;
    }

    //get all food from a specific restaurant
    public ArrayList<FoodModel> getFoods(String res_username) throws SQLException{
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT * FROM food WHERE res_username = '%s';", res_username)
        );
        ArrayList<FoodModel> foodModels = new ArrayList<>();
        while(resultSet.next()){
            String foodname = resultSet.getString("foodname");
            double price = resultSet.getDouble("price") ;
            String type = resultSet.getString("type");
            FoodModel foodModel = new FoodModel(res_username, foodname, price, type);
            foodModels.add(foodModel);
        }
        return foodModels;
    }
}
