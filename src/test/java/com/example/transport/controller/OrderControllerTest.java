package com.example.transport.controller;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;

    @Test
    public void getWaitingOrders() throws Exception{
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        // 发送get请求：
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8080/secure/waiting/9")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getInTransOrders()throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        // 发送get请求：
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8080/secure/intrans/7")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getMyHistoryOrders()throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        // 发送get请求：
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8080/secure/myhistory/1")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getReceiveOrders()throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        // 发送get请求：
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8080/secure/receive")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getMyInTransOrders()throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        // 发送get请求：
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8080/secure/myintrans/2")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getMyTransHistoryOrders()throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        // 发送get请求：
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8080/secure/mytranshistory/2")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
