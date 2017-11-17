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
            throws SQLException {
        connector.executeUpdate(String.format("INSERT INTO rating VALUES(%d,%d,%d,'%s','%s');",order_id,receiver_to_sender_rate,receiver_to_rest_rate,receiver_to_sender_comment,receiver_to_rest_comment));

        ResultSet rs1 = connector.executeQuery(String.format("SELECT O.sender_name, AVG(R.receiver_to_sender_rate) AS avg_sender" +
                "FROM rating R, order_info O" +
                "WHERE R.order_id = O.order_id AND" +
                "O.order_id = %d;", order_id));

        while(rs1.next()){
            String sender_name = rs1.getString("sender_name");
            int avg_sender = rs1.getShort("avg_sender");
            connector.executeUpdate(String.format("UPDATE sender SET sender_rating = %d WHERE username = '%s';",avg_sender,sender_name));
        }

        ResultSet rs2 = connector.executeQuery(String.format("SELECT O.restaurant_name, AVG(R.receiver_to_rest_rate) AS avg_rest" +
                "FROM rating R, order_info O" +
                "WHERE R.order_id = O.order_id AND" +
                "O.order_id = %d;", order_id));

        while(rs2.next()){
            String rest_name = rs2.getString("restaurant_name");
            int avg_rest = rs1.getShort("avg_rest");
            connector.executeUpdate(String.format("UPDATE restaurant SET restaurant_rating = %d WHERE username = '%s';",avg_rest,rest_name));
        }
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
            ratingModel = new RatingModel(order_id,senderRate,restRate,senderComment,restComment);
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

