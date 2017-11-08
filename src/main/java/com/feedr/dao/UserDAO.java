package com.feedr.dao;

import com.feedr.Models.UserModel;
import com.feedr.util.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
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

    public void creatUser(String username, String password, String phone) throws SQLException {
        connector.executeQuery(
                String.format("INSERT INTO user VALUES (%s,%s,%s);", username, password, phone)
        );
    }

    public ArrayList<UserModel> getUsers() throws SQLException {
        ResultSet resultSet = connector.executeQuery(
                "SELECT * FROM user;"
        );
        ArrayList<UserModel> userModels = new ArrayList<>();
        while(resultSet.next()) {
            String username = resultSet.getString("username");
            String phone = resultSet.getString("phone");
            UserModel userModel = new UserModel(username,phone);
            userModels.add(userModel);
        }
        return userModels;
    }

    public UserModel getUser(String username) throws SQLException {
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT username, phone FROM user WHERE username = %s;", username)
        );
        String phone = resultSet.getString("phone");
        UserModel userModel = new UserModel(username, phone);
        return userModel;
    }

    public String getPassword(String userame) throws SQLException {
        ResultSet resultSet = connector.executeQuery(
                String.format("SELECT password FROM user WHERE username = %s;", userame)
        );
        String password = resultSet.getString("password");
        return password;
    }

    public void updatePassword(String username, String oldPassword, String newPassword) throws SQLException {
        connector.executeQuery(
                String.format("UPDATE user SET password = %s WHERE username = %s AND password = %s;",
                        newPassword, username, oldPassword)
        );
    }

    public void updatePhone(String username, String newPhone) throws SQLException {
        connector.executeQuery(
                String.format("UPDATE user SET phone = %s WHERE username = %s;", newPhone,username)
        );
    }

    public void deleteUser(String username) throws SQLException {
        connector.executeQuery(
                String.format("DELETE FROM user WHERE username = %s;", username)
        );
    }







}
