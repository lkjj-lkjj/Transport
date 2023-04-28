package com.example.transport.dao;

import com.example.transport.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    @Rollback
    public void findByUsernameAndPassword()throws Exception{
        System.out.println(userDao.findByUsernameAndPassword("wsx","123"));
    }

    @Test
    @Rollback
    public void insertUser()throws Exception{
        User user=new User();
        user.setUsername("www");
        user.setPassword("111");
        user.setAuth(1);
        userDao.insertUser(user);
        System.out.println("success");
    }
}

