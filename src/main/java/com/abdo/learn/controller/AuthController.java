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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {
   final private AuthService authService;

   @Operation(description = "registration", summary = "register new account")
   @PostMapping("/register")
   public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegisterRequest user) {
      return ResponseEntity.ok(authService.registration(user));
   }

   @Operation(description = "Login", summary = "login to get token")
   @PostMapping("/login")
   public ResponseEntity<String> login(@Valid @RequestBody UserLoginRequest user) {
      return ResponseEntity.ok(authService.login(user));
   }

}
