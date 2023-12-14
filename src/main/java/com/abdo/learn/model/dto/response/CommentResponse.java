package com.abdo.learn.model.dto.response;

import java.time.LocalDateTime;

public record CommentResponse( Long id, String content,LocalDateTime createdAt, UserIdResponse user ) {
    
}
