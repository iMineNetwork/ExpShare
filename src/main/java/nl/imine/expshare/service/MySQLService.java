package nl.imine.expshare.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLService {

    private String username;
    private String password;
    private String jdbcUrl;

    private transient Connection connection;

    public MySQLService(String username, String password, String jdbcUrl) {
        this.username = username;
        this.password = password;
        this.jdbcUrl = jdbcUrl;
    }

    public void connect(){
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            System.err.println("Unable to connect to database | " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    public PreparedStatement query(String query){
        try {
            if(connection.isClosed()){
                connect();
            }
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            System.err.println("Unable to INSERT | " + e.getClass().getSimpleName() + ": " + e.getMessage());
            return null;
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
