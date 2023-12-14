package com.abdo.learn.service;


import com.abdo.learn.model.dto.request.CommentRequest;
import com.abdo.learn.model.dto.response.CommentResponse;

public interface CommentService {

    CommentResponse createComment(CommentRequest commentRequest);

    Boolean deleteComment(Long id);
    
}
