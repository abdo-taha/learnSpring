package com.abdo.learn.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CommentRequest(String content, @NotNull Long postId) {

}