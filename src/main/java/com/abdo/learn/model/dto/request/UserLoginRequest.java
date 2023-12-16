package com.abdo.learn.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record UserLoginRequest(@NotEmpty @Email String email, @NotEmpty String password) {

}
