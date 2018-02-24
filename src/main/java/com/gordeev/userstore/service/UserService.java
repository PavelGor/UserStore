package com.gordeev.userstore.service;

import com.gordeev.userstore.dao.UserDao;
import com.gordeev.userstore.entity.User;
import com.gordeev.userstore.locator.ServiceLocator;

import java.util.List;

public class UserService {
    private UserDao userDao = (UserDao) ServiceLocator.getService("userDao");
    private final static UserService instance = new UserService();

    private UserService() {
    }

    public static UserService getInstance() {
        return instance;
    }

    public List<User> getAll(){
        return userDao.getAll();
    }

    public void add(User user){
        userDao.add(user);
    }

    public User getById(int id) { return userDao.getById(id);}

    public void update(User user) { userDao.update(user);}
}
