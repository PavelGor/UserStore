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
    private static final String JDBC_DATABASE_URL = "jdbc:mysql://localhost/userstore?useUnicode=true&characterEncoding=UTF8";
    /**
     * User and Password
     */
    private static final String USER = "root";
    private static final String PASSWORD = "root";

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
    }


    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")){
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
    public void add(User user)  {
        String sql = "INSERT INTO users (firstName, lastName, salary, dateOfBirth) VALUES ( ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            Date date = Date.valueOf(user.getDateOfBirth());
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDouble(3, user.getSalary());
            statement.setDate(4, date);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getById(int id) {
        User user = new User();
        String sql = "SELECT * FROM users WHERE id = " + id;
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
    public void update(User user) {
        Date date = Date.valueOf(user.getDateOfBirth());
        String sql = "UPDATE users SET  firstName = ?, lastName = ?, salary = ?, dateOfBirth = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDouble(3, user.getSalary());
            statement.setDate(4, date);
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
