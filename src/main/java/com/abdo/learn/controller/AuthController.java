package com.abdo.learn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdo.learn.model.dto.request.UserLoginRequest;
import com.abdo.learn.model.dto.request.UserRegisterRequest;
import com.abdo.learn.model.dto.response.UserResponse;
import com.abdo.learn.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
   final private AuthService authService;

   @PostMapping("/register")
   public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegisterRequest user) {
      return ResponseEntity.ok(authService.registration(user));
   }

   @PostMapping("/login")
   public ResponseEntity<String> login(@Valid @RequestBody UserLoginRequest user) {
      return ResponseEntity.ok(authService.login(user));
   }

}
