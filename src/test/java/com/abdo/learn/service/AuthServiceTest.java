package com.abdo.learn.service;

import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.abdo.learn.mapper.UserMapper;
import com.abdo.learn.model.dto.request.UserRegisterRequest;
import com.abdo.learn.model.dto.response.UserResponse;
import com.abdo.learn.model.entity.UserEntity;
import com.abdo.learn.service.impl.AuthServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UsersService usersService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    private UserEntity userEntity;
    private UserResponse userResponse;
    private UserRegisterRequest userRegisterRequest;

    @BeforeEach
    public void init() {
        userEntity = UserEntity.builder()
                .email("test@mail.com")
                .password("password")
                .name("user name")
                .id(1L)
                .build();
        userResponse = UserMapper.INSTANCE.userEntityToUserResponse(userEntity);
        userRegisterRequest = UserRegisterRequest.builder()
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .build();
    }

    @Test
    public void AuthService_Registration_ReturnUser() {
        when(usersService.isEmailRegistered(userRegisterRequest.email())).thenReturn(false);
        when(usersService.createUser(Mockito.any(UserRegisterRequest.class))).thenReturn(userResponse);
        when(passwordEncoder.encode(Mockito.any(String.class))).thenReturn("12345678");

        UserResponse user = authService.registration(userRegisterRequest);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.email()).isEqualTo(userRegisterRequest.email());
        Assertions.assertThat(user.id()).isGreaterThan(0);
    }

}
