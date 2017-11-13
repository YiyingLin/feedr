package com.feedr.dao;

import com.feedr.models.ReceiverModel;
import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReceiverDao {
    private DatabaseConnector connector;

    @Autowired
    public ReceiverDao(DatabaseConnector connector) {
        this.connector = connector;
    }

    public void createReceiver(String username, int senderRating, String location) throws SQLException {
        connector.executeQuery(
                String.format("INSERT INTO receiver values (%s);", username)
        );
    }

    public ArrayList<ReceiverModel> getReceivers() throws SQLException{
        ResultSet resultset = connector.executeQuery(
                String.format("SELECT receiver.username, phone FROM receiver,user " +
                        "WHERE receiver.username = user.username;")
        );
        ArrayList<ReceiverModel> receivers = new ArrayList<>();
        while(resultset.next()){
            String username = resultset.getString("username");
            String phone = resultset.getString("phone");
            ReceiverModel receiver = new ReceiverModel(username,phone);
            receivers.add(receiver);
        }
        return receivers;
    }

    public ReceiverModel getReceiver(String username) throws SQLException {
        ResultSet resultset = connector.executeQuery(
                String.format("SELECT receiver.username, phone FROM receiver,user" +
                        "WHERE receiver.username = user.username AND receiver.username = %s;", username)
        );
        String receiverName = resultset.getString("username");
        String phone = resultset.getString("phone");
        ReceiverModel receiver = new ReceiverModel(receiverName, phone);
        return receiver;
    }

    public void deleteReceiver(String username) throws SQLException {
        connector.executeQuery(
                String.format("DELETE FROM receiver WHERE username = %s;", username)
        );
    }

    // TODO: receiver creates order

    // TODO: receiver checks orders
}
