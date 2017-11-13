package com.feedr.dao;

import com.feedr.models.CountSenderModel;
import com.feedr.models.SenderModel;
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

    //join 2 tables

    //join 2 tables




}
