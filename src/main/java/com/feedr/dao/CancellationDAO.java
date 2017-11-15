package com.feedr.dao;

import com.feedr.models.CancellationModel;
import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class CancellationDAO {

    private DatabaseConnector connector;

    @Autowired
    public CancellationDAO(DatabaseConnector connector) {
        this.connector = connector;
    }

    public void createCancellation(int order_id, String username,String reason)throws SQLException {
        connector.executeQuery(
                String.format("INSERT INTO cancellation VALUES (%d,'%s',CURRENT_TIMESTAMP,'%s');", order_id,username,reason)
        );
    }

    public ArrayList<CancellationModel> getCancellations() throws SQLException{
        ResultSet resultSet = connector.executeQuery(
                "SELECT * FROM cancellation;"
        );
        ArrayList<CancellationModel> cancellationModels = new ArrayList<>();
        while(resultSet.next()) {
            int order_id = resultSet.getInt("order_id");
            String username = resultSet.getString("username");
            Timestamp cancel_time = resultSet.getTimestamp("cancel_time");
            String reason = resultSet.getString("reason");
            CancellationModel cancellationModel = new CancellationModel(order_id,username,cancel_time,reason);
            cancellationModels.add(cancellationModel);
        }
        return cancellationModels;
    }

    public CancellationModel getCancellation(int order_id) throws SQLException{
        ResultSet resultSet = connector.executeQuery(
               String.format( "SELECT * FROM cancellation WHERE order_id = %d;", order_id)
        );

        String username = resultSet.getString("username");
        Timestamp cancel_time = resultSet.getTimestamp("cancel_time");
        String reason = resultSet.getString("reason");
        CancellationModel cancellationModel = new CancellationModel(order_id,username,cancel_time,reason);

        return cancellationModel;
    }
}
