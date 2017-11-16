package com.feedr.dao;

import com.feedr.models.RatingModel;
import com.feedr.models.UserRatingModel;
import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RatingDAO {
    private DatabaseConnector connector;

    @Autowired
    public RatingDAO(DatabaseConnector connector) {
        this.connector = connector;
    }

    public void createRating(int order_id, int receiver_to_sender_rate, int receiver_to_rest_rate, String receiver_to_sender_comment, String receiver_to_rest_comment)
    throws SQLException{
        connector.executeUpdate(String.format("INSERT INTO rating VALUES(%d,%d,%d,'%s','%s');",order_id,receiver_to_sender_rate,receiver_to_rest_rate,receiver_to_sender_comment,receiver_to_rest_comment));
    }

    public RatingModel getRating(int order_id) throws SQLException{
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT * FROM rating WHERE order_id = %d;", order_id)
        );
        RatingModel ratingModel = null;
        while(resultSet.next()){
            int senderRate = resultSet.getInt("receiver_to_sender_rate");
            int restRate = resultSet.getInt("receiver_to_rest_rate");
            String senderComment = resultSet.getString("receiver_to_sender_comment");
            String restComment = resultSet.getString("receiver_to_rest_comment");
            ratingModel = new RatingModel(order_id,senderRate,restRate,senderComment,restComment)
        }
        return ratingModel;
    }

    public UserRatingModel getSenderRating(String username) throws SQLException{
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT O.sender_name, AVG(R.receiver_to_sender_rate) AS average_rating" +
                        "FROM rating R, order_info O" +
                        "WHERE R.order_id = O.order_id AND" +
                        "O.sender_name = '%s';", username)
        );
        UserRatingModel model = null;
        while(resultSet.next()){
            int avgrating = resultSet.getInt(2);
            model = new UserRatingModel(username, avgrating);
        }
        return model;
    }

    public UserRatingModel getRestaurantRating(String username) throws SQLException{
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT O.sender_name, AVG(R.receiver_to_sender_rate) AS average_rating" +
                        "FROM rating R, order_info O" +
                        "WHERE R.order_id = O.order_id AND" +
                        "O.restaurant_name = '%s';", username)
        );
        UserRatingModel model = null;
        while(resultSet.next()){
            int avgRating = resultSet.getInt(2);
            model = new UserRatingModel(username, avgRating);
        }
        return model;
    }



}

