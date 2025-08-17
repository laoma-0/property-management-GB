package org.example.controller;

import org.example.PropertyManagementGbApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = PropertyManagementGbApplication.class)
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLoginEndpointExists() throws Exception {
        // 测试登录端点是否存在
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"test\",\"password\":\"test\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUserInfoEndpointExists() throws Exception {
        // 测试用户信息端点是否存在
        mockMvc.perform(post("/auth/info"))
                .andExpect(status().isOk());
    }

    @Test
    void testLogoutEndpointExists() throws Exception {
        // 测试退出登录端点是否存在
        mockMvc.perform(post("/auth/logout"))
                .andExpect(status().isOk());
    }
}