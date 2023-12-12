package com.abdo.learn.model.dto.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserResponse(
    @NotNull Long id,
    @NotEmpty String email,
    @NotEmpty
    String name
     ) {
} 
