package app;

import java.sql.*;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws DAOExeption {
        String username = System.getenv("MYSQL_USER");
        String password = System.getenv("MYSQL_PASSWORD");
        String database = System.getenv("MYSQL_DB");
        String url = "jdbc:mysql://127.0.0.1:3306/" + database;

        User alex = new User("Alex", 32, "Programist", 88.5F);
        User vasyl = new User("Vasyl", 45, "builder", 115.8F);
        User bob = new User("Bob", 54, "sailer", 65.8F);

        DatabaseConnector connection = new DatabaseConnector();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        EmployeeDAO employeeDAO = new EmployeeDAO(connection.connect(url, username, password));
        try {
//             add
            Optional<User> add = employeeDAO.addEmployee(vasyl);
//            update
            Optional<User> update = employeeDAO.updateEmployee(12, bob);
//            delete
            employeeDAO.deleteEmployee(4);
//            all list
            employeeDAO.findAll();
        } finally {
            connection.disconnect(connection.connect(url, username, password));
        }

    }
}