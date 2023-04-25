package com.example.transport.service;

import com.example.transport.entity.User;

import java.util.List;

public interface UserService {
    User findByUsernameAndPassword(String username, String password);
    List<User> findAllUsers();
    void registerAuthUser(User user);
}
