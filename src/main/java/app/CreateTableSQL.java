package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableSQL {
    public void createTable(Connection connection){
        String createTableSQL = "create table employees(\n" +
                "\tid INT primary key auto_increment,\n" +
                "\tname VARCHAR(50),\n" +
                "\tage INT,\n" +
                "\tpositions VARCHAR(100),\n" +
                "\tsalary FLOAT\n" +
                ");";

        try (Statement statement = connection.createStatement()){
            statement.execute(createTableSQL);
            System.out.println("Table create success");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
