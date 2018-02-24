package com.gordeev.userstore.dao.jdbc.mapper;

import com.gordeev.userstore.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserMapper {

    public User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt("id"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setSalary(resultSet.getDouble("salary"));
        LocalDate localDate = resultSet.getDate("dateOfBirth").toLocalDate();
        user.setDateOfBirth(localDate);

        return user;
    }
}
