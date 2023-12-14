package com.abdo.learn.model.dto.request;

import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreatePostRequest(@NotEmpty @NotNull String content, Set<UUID> photos) {
} 
