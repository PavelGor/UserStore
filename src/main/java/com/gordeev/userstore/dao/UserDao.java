package com.gordeev.userstore.dao;

import com.gordeev.userstore.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
    void addUser(User user);
    User getUserById(int id);
    void updateUser(User user);
}
