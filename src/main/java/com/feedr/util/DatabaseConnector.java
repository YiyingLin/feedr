package com.feedr.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DatabaseConnector {
    private Connection connection;

    @Autowired
    public DatabaseConnector(DriverManagerDataSource dataSource) throws SQLException {
        connection = dataSource.getConnection();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs =  stmt.executeQuery(query);
        return rs;
    }

    public void executeUpdate(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
        stmt.close();
    }

}