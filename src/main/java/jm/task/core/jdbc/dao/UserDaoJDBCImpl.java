package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        User user = new User();
        String sqlCommand = "CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL,age TINYINT NOT NULL)";

        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sqlCommand);
        }
    }

    public void dropUsersTable() throws SQLException {
        User user = new User();

        String sqlCommand = "DROP TABLE IF EXISTS user";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sqlCommand);
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        User user = new User();
        String sqlCommand = "INSERT INTO user (NAME, LASTNAME, AGE) VALUES(?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
        }
    }

    public void removeUserById(long id) throws SQLException {
        User user = new User();

        String sqlCommand = "DELETE FROM user WHERE ID = id";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)){
            preparedStatement.executeUpdate();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sqlCommand = "SELECT * FROM user";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                userList.add(user);
            }
            return userList;
        }
    }

    public void cleanUsersTable() throws SQLException {

        String sqlCommand = "DELETE FROM user" ;
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sqlCommand);
        }
    }
}
