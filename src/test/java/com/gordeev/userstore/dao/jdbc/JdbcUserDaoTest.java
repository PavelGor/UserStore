package com.gordeev.userstore.dao.jdbc;

import com.gordeev.userstore.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcUserDaoTest {
    JdbcUserDao jdbcUserDao = new JdbcUserDao();

    @Before
    public void before(){
        // can we create testDB and fill it with our data?
    }

    @Test
    public void getAll() {
        List<User> all = jdbcUserDao.getAll();
        for (User user : all) {
            assertNotNull(user);
            assertNotEquals(0,user.getId());
        }
    }

    @Test
    public void addUser() {
    }

    @Test
    public void getUserById() {
        User user = jdbcUserDao.getById(11);
        assertNotNull(user);
        assertNotEquals(0,user.getId());
    }

    @Test
    public void updateUser() {
    }
}