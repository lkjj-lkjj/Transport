package com.example.transport.controller;

import com.alibaba.fastjson.JSON;
import com.example.transport.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)  //让JUnit运行Spring的测试环境,获得Spring环境的上下文的支持
@SpringBootTest
public class LoginControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;


    @Test
    public void login() throws Exception{
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        User user = new User();
        user.setUsername("wxx");
        user.setPassword("223");


        String strJson = JSON.toJSONString(user);
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:8080/login")
                                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(strJson)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void register() throws Exception{
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        User user = new User();
        user.setUsername("wsx2");
        user.setPassword("123");


        String strJson = JSON.toJSONString(user);
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:8080/register")
                                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(strJson)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
