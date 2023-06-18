package com.example.transport.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)  //让JUnit运行Spring的测试环境,获得Spring环境的上下文的支持
@SpringBootTest
public class ContractControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;


    @Test
    public void toIndexView () throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();

        // 发送get请求：
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.get("http://localhost:8080/index")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }
}
