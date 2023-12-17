package com.abdo.learn.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.abdo.learn.exception.NotFoundException;
import com.abdo.learn.mapper.UserMapper;
import com.abdo.learn.model.dto.request.UserRegisterRequest;
import com.abdo.learn.model.dto.response.UserResponse;
import com.abdo.learn.model.entity.UserEntity;
import com.abdo.learn.repository.UserRepository;
import com.abdo.learn.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

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
    public void UserService_Create_ReturnUser() {
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.UserRegisterRequestToUserEntity(userRegisterRequest)).thenReturn(userEntity);
        when(userMapper.userEntityToUserResponse(userEntity)).thenReturn(userResponse);
        UserResponse savedUser = userService.createUser(userRegisterRequest);

        Assertions.assertThat(savedUser.id()).isGreaterThan(0);
    }

    @Test
    public void UserService_GetUser_ReturnUser() {
        when(userMapper.userEntityToUserResponse(userEntity)).thenReturn(userResponse);
        when(userRepository.findById(anyLong())).thenAnswer(
                invocation -> {
                    Long id = invocation.getArgument(0);
                    if (id == userEntity.getId())
                        return Optional.of(userEntity);
                    else
                        throw new NotFoundException(HttpStatus.NOT_FOUND, "");
                });
        // when(userRepository.findById(userEntity.getId())).thenReturn(Optional.of(userEntity));

        UserResponse user = userService.getUser(userEntity.getId());
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.id()).isGreaterThan(0);
        Assertions.assertThatThrownBy(() -> {
            userService.getUser(0L);
        }).isInstanceOf(NotFoundException.class);
    }

}
