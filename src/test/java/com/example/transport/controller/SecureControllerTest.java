package com.example.transport.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import javax.servlet.http.HttpServletRequest;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)  //让JUnit运行Spring的测试环境,获得Spring环境的上下文的支持
@SpringBootTest
public class SecureControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;

    HttpServletRequest requestMock = Mockito.mock(HttpServletRequest.class);

    @Test
    public void login()throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("POST");
        request.setRequestURI("/secure/getUserInfo");
        request.addHeader("Content-Type", "application/json");
        request.setContent("{\"username\":\"wxx\",\"password\":\"223\",\"userid\":\"12\",\"auth\":\"0\"}".getBytes());
        request.addHeader("token", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjExMSIsImlkIjo5LCJ1c2VyTmFtZSI6IndzeCIsImV4cCI6MTY4NTI4MzU5OSwiaWF0IjoxNjg1MjgxNzk5fQ.kwVBXXaRcV70dudviIMLsrpnRVpCprp_2Q1SYxMQkoU");

        mvc = MockMvcBuilders.webAppContextSetup(wac).build();

        System.out.println(request);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/secure/getUserInfo")
                        .content(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"username\":\"wxx\",\"password\":\"223\",\"userid\":\"12\",\"auth\":\"0\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("token", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjExMSIsImlkIjo5LCJ1c2VyTmFtZSI6IndzeCIsImV4cCI6MTY4NTI4MzU5OSwiaWF0IjoxNjg1MjgxNzk5fQ.kwVBXXaRcV70dudviIMLsrpnRVpCprp_2Q1SYxMQkoU"))
                .andExpect(status().isOk())
                .andReturn();


    }
}
