package com.example.auth_service.service;

import com.example.auth_service.entity.User;

import java.util.List;

public interface UserService {
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    List<User> findAllUsers();
    void registerAuthUser(User user);
}
