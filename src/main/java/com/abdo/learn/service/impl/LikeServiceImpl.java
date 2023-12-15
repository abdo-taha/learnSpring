package com.abdo.learn.service.impl;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.abdo.learn.exception.NotFoundException;
import com.abdo.learn.exception.UnknownErrorException;
import com.abdo.learn.model.dto.request.LikePostRequest;
import com.abdo.learn.model.entity.LikePostEntity;
import com.abdo.learn.repository.LikePostRepository;
import com.abdo.learn.repository.PostRepository;
import com.abdo.learn.service.AuthService;
import com.abdo.learn.service.LikeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    final private AuthService authService;
    final private LikePostRepository likePostRepository;
    final private PostRepository postRepository;

    @Override
    public Boolean createLike(LikePostRequest likePostRequest) {
        if (isLiked(likePostRequest))
            return false;
        LikePostEntity like = LikePostEntity.builder()
                .createdAt(LocalDateTime.now())
                .post(postRepository.findById(likePostRequest.postId()).get())
                .user(authService.getCurrentUserEntity())
                .build();
        likePostRepository.save(like);
        return true;

    }

    @Override
    public Boolean removeLike(LikePostRequest likePostRequest) {
        LikePostEntity like = likePostRepository.findByPostAndUser(
                postRepository.findById(likePostRequest.postId())
                        .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND, "post not found")),
                authService.getCurrentUserEntity())
                .orElseThrow(() -> new UnknownErrorException(HttpStatus.BAD_REQUEST, ""));
        likePostRepository.delete(like);
        return true;
    }

    @Override
    public Boolean isLiked(LikePostRequest likePostRequest) {

        return likePostRepository.findByPostAndUser(
                postRepository.findById(likePostRequest.postId())
                        .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND, "post not found")),
                authService.getCurrentUserEntity()).isPresent();

    }

}
