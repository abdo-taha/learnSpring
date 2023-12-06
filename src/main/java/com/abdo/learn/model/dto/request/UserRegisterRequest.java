package com.abdo.learn.model.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserRegisterRequest(
        @NotEmpty @Email String email,
        @NotNull  String password,
        @NotEmpty String name) {
} 
