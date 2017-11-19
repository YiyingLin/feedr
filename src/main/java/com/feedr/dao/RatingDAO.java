package com.feedr.dao;

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

    /**
     * create a new rating.
     * First insert into rating table,
     * then we need to calculate the new ratings for sender and restaurant, and update corresponding
     *  attributes
     * */
    public void createRating(int order_id, int receiver_to_sender_rate, int receiver_to_rest_rate, String receiver_to_sender_comment, String receiver_to_rest_comment)
            throws SQLException {
        connector.executeUpdate(String.format("INSERT INTO rating VALUES(%d,%d,%d,'%s','%s');",order_id,receiver_to_sender_rate,receiver_to_rest_rate,receiver_to_sender_comment,receiver_to_rest_comment));

        // get name of sender and restaurant being rated
        ResultSet resSenderResaurantName = connector.executeQuery(String.format("SELECT sender_name, restaurant_name from order_info WHERE order_id = %d;", order_id));
        resSenderResaurantName.next();
        String senderName = resSenderResaurantName.getString("sender_name");
        String restaurantName = resSenderResaurantName.getString("restaurant_name");

        //update sender rating
        ResultSet resAvgSender = connector.executeQuery(
                String.format("SELECT O.sender_name, AVG(R.receiver_to_sender_rate) AS avg_sender\n" +
                        "FROM rating R, order_info O\n" +
                        "WHERE O.order_id=R.order_id AND O.sender_name='%s'\n" +
                        "GROUP BY O.sender_name", senderName
                )
        );
        resAvgSender.next();
        int avg_sender = resAvgSender.getShort("avg_sender");
        connector.executeUpdate(String.format("UPDATE sender SET sender_rating = %d WHERE username = '%s';",avg_sender,senderName));

        //update restaurant rating
        ResultSet resAvgRestaurant = connector.executeQuery(
                String.format(
                        "SELECT O.restaurant_name, AVG(R.reveiver_to_rest_rate) AS avg_rest\n" +
                                "FROM rating R, order_info O\n" +
                                "WHERE O.order_id=R.order_id AND O.restaurant_name='%s'\n" +
                                "GROUP BY O.restaurant_name;",
                        restaurantName)
        );
        resAvgRestaurant.next();
        int avg_rest = resAvgRestaurant.getShort("avg_rest");
        connector.executeUpdate(String.format("UPDATE restaurant SET restaurant_rating = %d WHERE username = '%s';",avg_rest,restaurantName));
    }
}

