package app;

import java.sql.*;

public class DatabaseConnector {

    public Connection connect(String url, String username, String password){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e){
            System.out.println("Error connection to db" + e.getMessage());
        }
        return connection;
    }
    public void disconnect(Connection connection){
        if (connection != null){
            try {
                connection.close();
                System.out.println("Disconnect");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Already disconnect");
        }
    }
}
