package com.example.transport.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractDaoTest {

    @Autowired
    private ContractDao contractDao;

    @Test
    public void findAll(){
        System.out.println(contractDao.findAll());
    }
}
