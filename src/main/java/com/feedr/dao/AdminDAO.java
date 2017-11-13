package com.feedr.dao;

import com.feedr.models.CountSenderModel;
import com.feedr.models.RestaurantModel;
import com.feedr.models.UserModel;
import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAO {
    private DatabaseConnector connector;

    @Autowired
    public AdminDAO(DatabaseConnector connector) {
        this.connector = connector;
    }

    //group by
    public ArrayList<CountSenderModel> senderRatingCount() throws SQLException{
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT COUNT(username) AS count FROM sender GROUP BY sender_rating ORDER BY sender_rating DESC;")
        );
        ArrayList<CountSenderModel> models = new ArrayList<>();
        while(resultSet.next()) {
            int count = resultSet.getInt("count");
            int sender_rating = resultSet.getInt("sender_rating");
            CountSenderModel model = new CountSenderModel(count, sender_rating);
            models.add(model);
        }
        return models;
    }

    //join 3 tables
    //Recently, Mercante is having a sales promotion with our feedr app, if a receiver ordered 3 or more almond croissants in a single order,
    //then he or she will receive a free almond croissant next time.
    //Find the receivers' username and phone number who ordered 3 or more  almond croissants
    public ArrayList<UserModel> usersWinFreeCroissant() throws SQLException{
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT U.username, U.phone" +
                        "FROM user U, order_info O, order_include_food I" +
                        "WHERE U.username = O.receiver_name AND" +
                        "O.order_id = I.order_id AND" +
                        "I.res_username = 'Mercante' AND"+
                        "I.foodname = 'Almond Croissant' AND" +
                        "I.food_quantity >= 3;")
        );
        ArrayList<UserModel> models = new ArrayList<>();
        while(resultSet.next()){
            String username = resultSet.getNString("username");
            String phone = resultSet.getString("phone");
            UserModel model = new UserModel(username,phone);
            models.add(model);
        }
        return models;
    }


    //join 2 or more tables
    //Find the username and phone number of users who spent more than CAD500 in total, feedr will have a CAD20 coupon for these users
    public ArrayList<UserModel> usersSpentMoreThan500() throws SQLException{
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT U.username, U.phone" +
                        "FROM user U, order_info O, delivered D" +
                        "WHERE U.username = O.receiver_name AND" +
                        "D.order_id = O.order_id AND" +
                        "SUM(D.final_total_cost) >= 500;")
        );
        ArrayList<UserModel> userModels = new ArrayList<>();
        while(resultSet.next()){
            String username = resultSet.getNString("username");
            String phone = resultSet.getString("phone");
            UserModel model;
            model = new UserModel(username,phone);
            userModels.add(model);
        }
        return userModels;
    }


    //join 2 or more tables
    //Find restaurants(all info) which xxx(username) ordered
    public ArrayList<RestaurantModel> perferedRestaurant(String username) throws SQLException{
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT R.username, R.resname, R.restaurant_rating, R.location, U.phone" +
                        "FROM restaurant R, order_info O, user U" +
                        "WHERE R.username = O.restaurant_name AND" +
                        "U.username = R.username" +
                        "O.receiver_name = %s", username)
        );
        ArrayList<RestaurantModel> resModels = new ArrayList<>();
        while(resultSet.next()){
            String res_username = resultSet.getNString("username");
            String res_phone = resultSet.getNString("phone");
            String res_name = resultSet.getString("resname");
            int res_rating = resultSet.getInt("restaurant_rating");
            String location = resultSet.getNString("location");
            RestaurantModel resModel;
            resModel = new RestaurantModel(res_username, res_phone, res_name, res_rating, location);
            resModels.add(resModel);
        }
        return resModels;


    }








}
