package com.abdo.learn.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.abdo.learn.mapper.PostMapper;
import com.abdo.learn.mapper.UserMapper;
import com.abdo.learn.model.dto.request.CreatePostRequest;
import com.abdo.learn.model.dto.request.EditPostRequest;
import com.abdo.learn.model.dto.response.PostResponse;
import com.abdo.learn.model.dto.response.UserResponse;
import com.abdo.learn.model.entity.PhotoEntity;
import com.abdo.learn.model.entity.PostEntity;
import com.abdo.learn.repository.PostRepository;
import com.abdo.learn.service.AuthService;
import com.abdo.learn.service.PhotoDBService;
import com.abdo.learn.service.PostService;
import com.abdo.learn.service.UsersService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    final private PostRepository postRepository;
    final private PostMapper postMapper;
    final private AuthService authService;
    final private UserMapper userMapper;
    final private PhotoDBService photoDBService;
    final private UsersService usersService;

    @Override
    public PostResponse createPost(CreatePostRequest postRequest) {
        PostEntity post = postMapper.PostRequestToPostEntity(postRequest);
        UserResponse user = authService.getCurrentUser();
        post.setUser(userMapper.UserResponseToUserEntity(user));
        Set<PhotoEntity> photos = new HashSet<>();
        for (UUID id : postRequest.photos()) {
            photos.add(photoDBService.getPhotoById(id));
        }
        post.setPhotos(photos);
        PostEntity savedPost =  postRepository.save(post);
        return postMapper.PostEntityToPostResponse(savedPost);
    }


    @Override
    public PostResponse getPostById(Long id) {
        PostEntity post = postRepository.findById(id).get();
        return postMapper.PostEntityToPostResponse(post);
    }


    @Override
    public Boolean deletePostById(Long id) {
        PostEntity post = postRepository.findById(id).get();
        UserResponse user = authService.getCurrentUser();
        if (user.id() == post.getUser().getId()) {
            postRepository.delete(post);
            return true;
        }
        return false;

    }


    @Override
    public PostResponse editPost(EditPostRequest post,Long id) {
       
        UserResponse user = authService.getCurrentUser();
        PostEntity originalPost = postRepository.findById(id).get();
        if (user.id() == originalPost.getUser().getId()) {
            originalPost.setContent(post.content());
            PostEntity savedPost = postRepository.save(originalPost);
            return postMapper.PostEntityToPostResponse(savedPost);
        }
        return null;
    }


    @Override
    public List<PostResponse> getPostsByUserId(Long id) {
        return postRepository
                .findByUser(usersService.getUserEntity(id))
                .stream()
                .map(postMapper::PostEntityToPostResponse)
                .toList();
    }


    
    
}
