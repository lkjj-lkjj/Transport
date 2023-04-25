package com.example.transport.dao;

import com.example.transport.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User findByUsernameAndPassword(String username, String password);

    @Insert("INSERT INTO user (username, password, auth) VALUES (#{username}, #{password}, #{auth})")
    void insertUser(User user);

    @Select("SELECT * FROM user")
    List<User> findALL();
}
