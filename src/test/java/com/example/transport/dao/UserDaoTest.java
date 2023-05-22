package com.example.transport.dao;

import com.example.transport.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    @Rollback
    public void findByUsernameAndPassword()throws Exception{
        System.out.println(userDao.findByUsernameAndPassword("lkj","123"));
    }

    @Test
    @Rollback
    public void insertUser()throws Exception{
        User user=new User();
        user.setUsername("wsx");
        user.setPassword("111");
        user.setAuth(0);
        userDao.insertUser(user);
        System.out.println("success");
    }

    @Test
    @Rollback
    public void findALL()throws Exception{
        List<User> userList ;
        userList = userDao.findALL();
        System.out.println(userList);
    }

    @Test
    @Rollback
    public void findByUsername()throws Exception{
        User user;
        user = userDao.findByUsername("lkj");
        System.out.println(user);
    }
}

