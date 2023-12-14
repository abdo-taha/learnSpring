package com.abdo.learn.service;

import java.util.List;

import com.abdo.learn.model.dto.request.CreatePostRequest;
import com.abdo.learn.model.dto.request.EditPostRequest;
import com.abdo.learn.model.dto.response.PostResponse;

public interface PostService {

    PostResponse createPost(CreatePostRequest post);
    
    PostResponse editPost(EditPostRequest post,Long id);

    PostResponse getPostById(Long id);

    Boolean deletePostById(Long id);

    List<PostResponse> getPostsByUserId(Long id);

    
} 
