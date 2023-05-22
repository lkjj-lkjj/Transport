package com.example.transport.controller;

import com.example.transport.entity.User;
import com.example.transport.service.UserService;
import com.example.transport.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.transport.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录Controller
 */
@Slf4j
@RestController
public class LoginController
{
    /**
     * 模拟用户 登录
     */
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user)
    {
        User result = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(result != null){
            String token = JwtUtil.createToken(result);
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("auth", result.getAuth());
            map.put("id", result.getUserid());
            return Result.success(map);
        }
        return Result.error("500","login error");
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
        if(userService.findByUsername(user.getUsername()) != null){
            return Result.error("500","user exist!");
        }
        userService.registerAuthUser(user);
        return Result.success();
    }
}
