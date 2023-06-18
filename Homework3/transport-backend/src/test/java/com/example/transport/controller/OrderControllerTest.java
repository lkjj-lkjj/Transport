package com.example.transport.controller;

import com.alibaba.fastjson.JSON;
import com.example.transport.entity.Order;
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

    @Test
    public void receiveOrderByTransporter()throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        Order order = new Order();
        order.setOrderid(3);

        String strJson = JSON.toJSONString(order);
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:8080/secure/receiveorder")
                                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(strJson)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void completeOrder() throws Exception{
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        Order order = new Order();
        order.setUserid(1);

        String strJson = JSON.toJSONString(order);
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:8080/secure/complete")
                                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(strJson)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void insertAnOrder() throws  Exception{
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        Order order = new Order();
        order.setUserid(9);
        order.setOrdername("susususu");
        order.setDescription("oooooooo");
        order.setCreatetime("2023-05-Mo 22:56:30");
        order.setState(0);

        String strJson = JSON.toJSONString(order);
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:8080/secure/insert")
                                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(strJson)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void revokeOrder() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        Order order = new Order();
        order.setOrderid(10);

        String strJson = JSON.toJSONString(order);
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:8080/secure/revoke")
                                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(strJson)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
