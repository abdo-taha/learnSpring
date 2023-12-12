package com.abdo.learn.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreatePostRequest(@NotEmpty @NotNull String content) {
} 
