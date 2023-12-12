package com.abdo.learn.service;

import com.abdo.learn.model.dto.request.UserRegisterRequest;
import com.abdo.learn.model.dto.response.UserResponse;
import com.abdo.learn.model.entity.UserEntity;

public interface UsersService {

    UserResponse createUser(UserRegisterRequest user);

    UserResponse getUser(Long id);

    Boolean isEmailRegistered(String email);

    UserEntity getUserEntity(Long id);
}
