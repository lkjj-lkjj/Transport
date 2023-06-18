package com.example.auth_service.controller;

import com.example.auth_service.entity.User;
import com.example.auth_service.kafka.EventProducer;
import com.example.auth_service.service.UserService;
import com.example.auth_service.util.JwtUtil;
import com.example.auth_service.util.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private EventProducer eventProducer;

    @HystrixCommand(fallbackMethod = "loginFallback")
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
            if(result.getAuth() == 0){
                eventProducer.sendEvent("user","Login Success!");
            }else{
                eventProducer.sendEvent("trans", "Login Success!");
            }
            return Result.success(map);
        }else {
            throw new RuntimeException("login throw RuntimeException");
        }
    }

    public Result<?> loginFallback(@RequestBody User user) {
        return Result.error("500","login error");
    }

    @HystrixCommand(fallbackMethod = "registerFallback")
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
        if(userService.findByUsername(user.getUsername()) != null){
//            return Result.error("500","user exist!");
            throw new RuntimeException("register throw RuntimeException");
        }
        userService.registerAuthUser(user);
        return Result.success();
    }

    public Result<?> registerFallback(@RequestBody User user) {
        return Result.error("500","user exist!");
    }
}
