package com.abdo.learn.model.dto.request;

import lombok.Builder;

@Builder
public record CommentRequest(String content,Long postId)
{
    
} 