package com.example.auth_service.service.impl;

import com.example.auth_service.dao.UserDao;
import com.example.auth_service.entity.User;
import com.example.auth_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findALL();
    }

    @Override
    public void registerAuthUser(User user) {
        user.setAuth(0);
        userDao.insertUser(user);
    }
}
