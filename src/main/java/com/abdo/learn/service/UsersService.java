package com.abdo.learn.service;

import com.abdo.learn.model.dto.request.UserRegisterRequest;
import com.abdo.learn.model.dto.response.UserResponse;

public interface UsersService {

    UserResponse createUser(UserRegisterRequest user);

    UserResponse getUser(Long id);
}
