package com.abdo.learn.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record UserLoginRequest(@NotEmpty String email,@NotEmpty String password) {

    
}
