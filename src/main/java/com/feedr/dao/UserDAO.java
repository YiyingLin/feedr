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

    public void creatUser(String username, String password, String phone) throws SQLException {
        connector.executeQuery(
                "INSERT INTO user VALUES ("+ username + "," + password + "," + phone + ");"
        );
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

    public String getPhone(String userame) throws SQLException {
        ResultSet resultSet = connector.executeQuery(
                "SELECT phone FROM user WHERE username = " + userame + ";"
        );
        String phone = resultSet.getString("phone");
        return phone;
    }

    public String getPassword(String userame) throws SQLException {
        ResultSet resultSet = connector.executeQuery(
                "SELECT password FROM user WHERE username = " + userame + ";"
        );
        String password = resultSet.getString("password");
        return password;
    }

    public void updatePassword(String username, String oldPassword, String newPassword) throws SQLException {
        connector.executeQuery(
                "UPDATE user SET password = "+ newPassword +" WHERE username = "+ username +" AND password = "+oldPassword+";"
        );
    }

    public void updatePhone(String username, String newPhone) throws SQLException {
        connector.executeQuery(
                "UPDATE user SET phone = "+ newPhone +" WHERE username = "+ username +";"
        );
    }

    public void deleteUser(String username) throws SQLException {
        connector.executeQuery(
                "DELETE FROM user WHERE username ="+ username+";"
        );
    }







}
