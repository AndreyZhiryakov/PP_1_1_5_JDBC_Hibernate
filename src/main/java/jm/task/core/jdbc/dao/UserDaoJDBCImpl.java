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
        Connection connection = Util.getConnection();
        Statement statement = null;

        String sqlCommand = "CREATE TABLE IF NOT EXISTS Users (ID BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
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
        Connection connection = Util.getConnection();
        Statement statement = null;
        String sqlCommand = "DROP TABLE Users";
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
        Connection connection = Util.getConnection();
        User user = new User();
        PreparedStatement preparedStatement = null;
        String sqlCommand = "INSERT INTO Users(ID, NAME, LASTNAME, AGE) VALUES(?,name,lastName,age)";
        try {
            preparedStatement = connection.prepareStatement(sqlCommand);
            preparedStatement.setLong(1,user.getId());
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
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        String sqlCommand = "DELETE FROM Users WHERE ID = id";
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
    String sqlCommand = "SELECT ID, NAME, LASTNAME, AGE";
    Statement statement = null;
    try {
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCommand);
        while (resultSet.next()) {
            User user = new User();
            //user.setId(resultSet.getLong("ID"));
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
        Connection connection = Util.getConnection();
        Statement statement = null;

        String sqlCommand = "DELETE FROM Users" ;
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
