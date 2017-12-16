package com.gordeev.userstore.dao.jdbc.mapper;

import com.gordeev.userstore.entity.User;
import org.junit.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserMapperTest {
    UserMapper userMapper = new UserMapper();

    @Test
    public void testMapRow() throws SQLException {
        User expectedUser = new User();
        expectedUser.setId(1);
        expectedUser.setFirstName("Tolik");
        expectedUser.setLastName("Tr");
        expectedUser.setSalary(3000);

        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("firstName")).thenReturn("Tolik");
        when(resultSet.getString("lastName")).thenReturn("Tr");
        when(resultSet.getDouble("salary")).thenReturn(3000.0);
        when(resultSet.getDate("dateOfBirth")).thenReturn(Date.valueOf("2000-01-01"));

        User actualUser = userMapper.mapRow(resultSet);

        assertNotNull(actualUser);
        assertEquals(expectedUser.getId(),actualUser.getId());
        assertEquals(expectedUser.getFirstName(),actualUser.getFirstName());
        assertEquals(expectedUser.getLastName(),actualUser.getLastName());
        assertEquals(expectedUser.getSalary(),actualUser.getSalary(), 0.1);
    }


}