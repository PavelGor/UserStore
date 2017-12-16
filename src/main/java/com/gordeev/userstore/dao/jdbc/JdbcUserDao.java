package com.gordeev.userstore.dao.jdbc;

import com.gordeev.userstore.dao.UserDao;
import com.gordeev.userstore.dao.jdbc.mapper.UserMapper;
import com.gordeev.userstore.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    /**
     * JDBC Driver and database url
     */
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_DATABASE_URL = "jdbc:mysql://localhost/developer";
    /**
     * User and Password
     */
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static final String GET_ALL_SQL = "SELECT * FROM users";
    private static final UserMapper USER_MAPPER = new UserMapper();

    private Connection connection;

    public JdbcUserDao()  {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_DATABASE_URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //where we must do - connection.close(); ?
    }


    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_SQL)){

            while (resultSet.next()) {
                result.add(USER_MAPPER.mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void addUser(User user)  {
        try (Statement statement = connection.createStatement()){

            Date date = Date.valueOf(user.getDateOfBirth());

            String sql = "INSERT INTO users (firstName, lastName, salary, dateOfBirth)" +
                    "VALUES ( '"+ user.getFirstName()
                    + "', '" + user.getLastName()
                    + "',"  + user.getSalary()
                    + ", '"  + date + "')";

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(int id) {

        User user = new User();

        String sql = "SELECT * FROM developer.users WHERE id = " + id;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            if (resultSet.next()) {
                user = USER_MAPPER.mapRow(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        Date date = Date.valueOf(user.getDateOfBirth());
        try (Statement statement = connection.createStatement()){

            String sql = "UPDATE users SET " +
                    " firstName = '" + user.getFirstName() + "'," +
                    " lastName = '" + user.getLastName() + "'," +
                    " salary = " + user.getSalary() + "," +
                    " dateOfBirth = '" + date + "' " +
                    " WHERE id = " + user.getId();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
