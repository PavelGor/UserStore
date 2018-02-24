package com.gordeev.userstore.dao;

import com.gordeev.userstore.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
    void add(User user);
    User getById(int id);
    void update(User user);
}
