package com.abdo.learn.service.impl;

import org.springframework.stereotype.Service;

import com.abdo.learn.model.dto.request.UserRegisterRequest;
import com.abdo.learn.model.dto.response.UserResponse;
import com.abdo.learn.model.entity.UserEntity;
import com.abdo.learn.repository.UserRepository;
import com.abdo.learn.service.UsersService;
import com.abdo.learn.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UsersService {

    final private UserRepository userRepository;
    final private UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRegisterRequest user) {
        UserEntity savedUser =  userRepository.save(userMapper.UserRegisterRequestToUserEntity(user));
        return userMapper.userEntityToUserResponse(savedUser);
    }
    
}
