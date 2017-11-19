package com.feedr.dao;

import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.SQLException;

@Component
public class CancellationDAO {

    private DatabaseConnector connector;

    @Autowired
    public CancellationDAO(DatabaseConnector connector) {
        this.connector = connector;
    }

    public void createCancellation(int order_id, String username,String reason)throws SQLException {
        connector.executeUpdate(
                String.format("INSERT INTO cancellation VALUES (%d,'%s',NOW(),'%s');", order_id,username,reason)
        );
    }
}
