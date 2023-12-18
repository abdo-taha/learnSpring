package com.abdo.learn.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.abdo.learn.config.JwtAuthenticationFilter;
import com.abdo.learn.mapper.UserMapper;
import com.abdo.learn.model.dto.request.UserRegisterRequest;
import com.abdo.learn.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.hamcrest.CoreMatchers;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private UserRegisterRequest userRegisterRequest;

    @MockBean
    private AuthService authService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    public void init() {
        userRegisterRequest = UserRegisterRequest.builder()
                .email("test@mail.com")
                .password("password")
                .name("user name")
                .build();
    }

    @Test
    public void AuthController_register_returnUser() throws Exception {
        when(authService.registration(any())).thenAnswer((invocations -> UserMapper.INSTANCE.userEntityToUserResponse(
                UserMapper.INSTANCE.UserRegisterRequestToUserEntity(invocations.getArgument(0)))));

        ResultActions response = mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegisterRequest)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(userRegisterRequest.email())));
    }

}
