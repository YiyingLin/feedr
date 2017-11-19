package com.feedr.dao;

import com.feedr.models.UserModel;
import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserDAO {
    private DatabaseConnector connector;

    @Autowired
    public UserDAO(DatabaseConnector connector) {
        this.connector = connector;
    }
    
    // I don't think this method should take resName and resLocation as parameters unless frontend can input empty Strings automatically.
    public void createUser(String username,
                           String password,
                           String phone,
                           String type,
                           String resName,
                           String resLocation) throws SQLException {
        connector.executeUpdate(
                String.format("INSERT INTO user VALUES ('%s','%s','%s', '%s');", username, password, phone, type)
        );
        if(type.equals("USER")){
            connector.executeUpdate(
                    String.format("INSERT INTO receiver VALUES ('%s');", username)
            );
            connector.executeUpdate(
                    String.format("INSERT INTO sender VALUES ('%s', 0,NULL);", username)
            );
        }
        if(type.equals("RESTAURANT")){
            connector.executeUpdate(
                    String.format("INSERT INTO restaurant VALUES ('%s','%s', 0, '%s');", username,resName,resLocation)
            );
        }
    }

    public UserModel getUser(String username) throws SQLException {
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT * FROM userInfo WHERE username = '%s';", username)
        );
        UserModel userModel = null;
        while (resultSet.next()) {
            String phone = resultSet.getString("phone");
            String type = resultSet.getString("type");
            userModel = new UserModel(username, phone,type);
        }
        return userModel;
    }

    public String getPassword(String userame) throws SQLException {
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT password FROM user WHERE username = '%s';", userame)
        );
        String password = null;
        while (resultSet.next()) {
            password = resultSet.getString("password");
        }
        return password;
    }

    public void deleteUser(String username) throws SQLException {
        connector.executeUpdate(
                String.format("DELETE FROM user WHERE username = '%s';", username)
        );
    }
}
