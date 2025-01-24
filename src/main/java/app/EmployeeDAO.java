package app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO {
    private final Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }
    public List<User> findAll () throws DAOExeption{
        try (Statement statement = connection.createStatement()){
            boolean success = statement.execute("select * from employees");
            if (!success){
                throw  new DAOExeption();
            }
            List<User> users = new ArrayList<>();

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                User newUser = new User();

                newUser.setId(resultSet.getInt("id"));
                newUser.setName(resultSet.getString("name"));
                newUser.setAge(resultSet.getInt("age"));
                newUser.setPositions(resultSet.getString("positions"));
                newUser.setSalary(resultSet.getFloat("salary"));
                users.add(newUser);
                System.out.println("id: " + newUser.getId() +
                        "; name: " + newUser.getName() +
                        "; age: " + newUser.getAge() +
                        "; positions: " + newUser.getPositions() +
                        "; salary: " + newUser.getSalary());
            }
            return users;
        } catch (SQLException e){
            throw new DAOExeption();
        }
    }

    public Optional<User> addEmployee(User user) throws DAOExeption{
        try (PreparedStatement statement = connection.prepareStatement("insert into employees(name, age, positions, salary) values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setString(3, user.getPositions());
            statement.setFloat(4, user.getSalary());
            int rowsUpdate = statement.executeUpdate();
            if (rowsUpdate == 0){
                return Optional.empty();
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()){
                user.setId(generatedKeys.getInt(1));
            }
            return Optional.of(user);
        } catch (SQLException e){
            throw new DAOExeption();
        }
    }

    public void updateEmployee(){}
    public void deleteEmployee(){}
}
