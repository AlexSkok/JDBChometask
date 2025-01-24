package app;

import java.sql.*;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws DAOExeption {
        String username = System.getenv("MYSQL_USER");
        String password = System.getenv("MYSQL_PASSWORD");
        String database = System.getenv("MYSQL_DB");
        String url = "jdbc:mysql://127.0.0.1:3306/" + database;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(url, username, password)){
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            employeeDAO.findAll();
//            add user
            User alex = new User();
            alex.setName("Alex");
            alex.setAge(33);
            alex.setPositions("worker");
            alex.setSalary(82);
            Optional<User> save = employeeDAO.addEmployee(alex);
            User saveAlex = save.get();

        } catch (SQLException e){
            System.out.println("error to connect to db");
        }
    }
}