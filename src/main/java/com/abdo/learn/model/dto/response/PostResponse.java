package com.abdo.learn.model.dto.response;

import java.time.LocalDateTime;

public record PostResponse(Long id, String content, UserResponse user,LocalDateTime createdAt) {
}
