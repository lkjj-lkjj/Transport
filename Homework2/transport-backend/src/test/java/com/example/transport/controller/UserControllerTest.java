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




@RunWith(SpringRunner.class)  //让JUnit运行Spring的测试环境,获得Spring环境的上下文的支持
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;


    @Test
    public void showLoginForm() throws Exception{
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        // 发送get请求：
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8080/login")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    public void toRegisterView () throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        // 发送get请求：
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8080/register")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void login() throws Exception{
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        User user = new User();
        user.setUsername("wsx");
        user.setPassword("111");

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
    public void register() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        User user = new User();
        user.setUsername("wsxsx");
        user.setPassword("223");
        user.setAuth(1);
        String strJson = JSON.toJSONString(user);
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:8080/register")
                                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(strJson)
                                .param("confirm","1")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }



}

