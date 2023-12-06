package com.abdo.learn.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abdo.learn.model.dto.request.UserLoginRequest;
import com.abdo.learn.model.dto.request.UserRegisterRequest;
import com.abdo.learn.model.dto.response.UserResponse;
import com.abdo.learn.service.AuthService;
import com.abdo.learn.service.UsersService;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{
    final private UsersService usersService;
    final private JwtServiceImpl jwtService;
    final private PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Override
    public UserResponse registration(UserRegisterRequest user) {
        //TODO add registration logic if any
        UserRegisterRequest securedUser = UserRegisterRequest.builder()
                .email(user.email())
                .password(passwordEncoder.encode(user.password()))
                .name(user.name())
                .build();
        UserResponse savedUser = usersService.createUser(securedUser);
        return savedUser;
    }

    @Override
    public String login(UserLoginRequest user) {
        //TODO learn
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.email(), user.password()));
        return jwtService.generateToken(user);
    }
    
}
