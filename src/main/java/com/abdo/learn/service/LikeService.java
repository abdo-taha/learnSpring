package com.abdo.learn.service;

import com.abdo.learn.model.dto.request.LikePostRequest;

public interface LikeService {

    Boolean createLike(LikePostRequest likePostRequest);

    Boolean removeLike(LikePostRequest likePostRequest);
    
    Boolean isLiked(LikePostRequest likePostRequest);
} 
