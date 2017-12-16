package com.gordeev.userstore.service;

import com.gordeev.userstore.dao.UserDao;
import com.gordeev.userstore.dao.jdbc.JdbcUserDao;
import com.gordeev.userstore.entity.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public class UserService {
    private UserDao userDao = new JdbcUserDao();

    public List<User> getAll(){
        return userDao.getAll();
    }

    public void addUser(User user){
        userDao.addUser(user);
    }

    public User getUserById(int id) { return userDao.getUserById(id);}

    public void updateUser(User user) { userDao.updateUser(user);}
}
