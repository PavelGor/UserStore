package com.gordeev.userstore.service;

import com.gordeev.userstore.entity.AuthorizedUser;

import java.util.ArrayList;
import java.util.List;

public class AuthorizedUserService {
    private List<AuthorizedUser> users = new ArrayList<>();

    public AuthorizedUserService() {
        users.add(new AuthorizedUser("user","user"));
        users.add(new AuthorizedUser("admin","admin"));
    }

    public AuthorizedUser autenticate(String name, String password){
        for (AuthorizedUser user : users) {
            if (user.getUsername().equals(name) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }
}
