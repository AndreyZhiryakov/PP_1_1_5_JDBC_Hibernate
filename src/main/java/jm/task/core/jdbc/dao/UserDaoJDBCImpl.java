package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        User user = new User();
        Connection connection = Util.getConnection();
        Statement statement = null;

        String sqlCommand = "CREATE TABLE IF NOT EXISTS user (ID BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                "NAME VARCHAR(45) NOT NULL, LASTNAME VARCHAR(45) NOT NULL,AGE TINYINT NOT NULL)";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null){
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        User user = new User();
        Connection connection = Util.getConnection();
        Statement statement = null;
        String sqlCommand = "DROP TABLE user";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null){
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        User user = new User();
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        String sqlCommand = "INSERT INTO user (NAME, LASTNAME, AGE) VALUES(?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(sqlCommand);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
          if (preparedStatement != null){
              preparedStatement.close();
          }
          if (connection != null) {
              connection.close();
          }
        }
    }

    public void removeUserById(long id) throws SQLException {
        User user = new User();
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        String sqlCommand = "DELETE FROM user WHERE ID = id";
        try {
            preparedStatement = connection.prepareStatement(sqlCommand);
            preparedStatement.executeUpdate();
         } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        Connection connection = Util.getConnection();
    List<User> userList = new ArrayList<>();
    String sqlCommand = "SELECT * FROM user";
    Statement statement = null;
    try {
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("ID"));
            user.setName(resultSet.getString("NAME"));
            user.setLastName(resultSet.getString("LASTNAME"));
            user.setAge(resultSet.getByte("AGE"));

            userList.add(user);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (statement != null){
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        User user = new User();
        Connection connection = Util.getConnection();
        Statement statement = null;

        String sqlCommand = "DELETE FROM user" ;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null){
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
