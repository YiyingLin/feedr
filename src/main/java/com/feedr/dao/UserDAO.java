package com.feedr.dao;

import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class UserDAO {
    private DatabaseConnector connector;

    @Autowired
    public UserDAO(DatabaseConnector connector) {
        this.connector = connector;
    }

    public String getUsers() throws SQLException {
        ResultSet resultSet = connector.executeQuery(
                "SELECT * FROM user;"
        );
        ArrayList<String> users = new ArrayList<>();
        while (resultSet.next()) {
            String useranme = resultSet.getString("username");
            String password = resultSet.getString("password");
            String phone = resultSet.getString("phone");
            users.add(String.format("%s %s %s", useranme, password, phone));
        }
        return users.toString();
    }
}
