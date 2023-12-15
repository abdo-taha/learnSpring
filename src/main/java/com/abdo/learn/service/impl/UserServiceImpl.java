package com.abdo.learn.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.abdo.learn.model.dto.request.UserRegisterRequest;
import com.abdo.learn.model.dto.response.UserResponse;
import com.abdo.learn.model.entity.UserEntity;
import com.abdo.learn.repository.UserRepository;
import com.abdo.learn.service.UsersService;
import com.abdo.learn.exception.NotFoundException;
import com.abdo.learn.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UsersService {

    final private UserRepository userRepository;
    final private UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRegisterRequest user) {
        UserEntity savedUser = userRepository.save(userMapper.UserRegisterRequestToUserEntity(user));
        return userMapper.userEntityToUserResponse(savedUser);
    }

    @Override
    public UserResponse getUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND, "user not found"));
        return userMapper.userEntityToUserResponse(user);
    }

    @Override
    public UserEntity getUserEntity(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND, "user not found"));
        return user;
    }

    @Override
    public Boolean isEmailRegistered(String email) {

        return userRepository.findByEmail(email).isPresent();
    }

}
