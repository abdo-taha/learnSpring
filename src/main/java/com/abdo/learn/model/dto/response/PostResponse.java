package com.abdo.learn.model.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record PostResponse(Long id, String content, UserIdResponse user,LocalDateTime createdAt,Set<PhotoResponse> photos,List<CommentResponse> comments) {
}
