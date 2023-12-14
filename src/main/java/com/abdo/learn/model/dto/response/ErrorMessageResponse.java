package com.abdo.learn.model.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Builder;

@Builder
public record ErrorMessageResponse(
    LocalDateTime createAt,
    String message,
    Map<String,Object> details    
) {
        
}