package com.abdo.learn.service;

import com.abdo.learn.model.dto.request.UserLoginRequest;
import com.abdo.learn.model.dto.request.UserRegisterRequest;
import com.abdo.learn.model.dto.response.UserResponse;

public interface AuthService {

    UserResponse registration(UserRegisterRequest user);

    String login(UserLoginRequest user);
}
