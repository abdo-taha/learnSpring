package com.abdo.learn.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.abdo.learn.mapper.CommentMapper;
import com.abdo.learn.model.dto.request.CommentRequest;
import com.abdo.learn.model.dto.response.CommentResponse;
import com.abdo.learn.model.entity.CommentEntity;
import com.abdo.learn.model.entity.UserEntity;
import com.abdo.learn.repository.CommentRepository;
import com.abdo.learn.repository.PostRepository;
import com.abdo.learn.service.AuthService;
import com.abdo.learn.service.CommentService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    final private CommentRepository commentRepository;
    final private PostRepository postRepository;
    final private AuthService authService;
    final private CommentMapper commentMapper;
    
    @Override
    public Boolean deleteComment(Long id) {
        CommentEntity commentEntity = commentRepository.findById(id).get();
        UserEntity user = authService.getCurrentUserEntity();
        if (user.getId() != commentEntity.getUser().getId())
            return false;
        commentRepository.deleteById(id);
        return true;
    }

    @Override
    public CommentResponse createComment(CommentRequest commentRequest) {
        CommentEntity comment = CommentEntity.builder()
            .content(commentRequest.content())
            .createdAt(LocalDateTime.now())
            .post(postRepository.findById(commentRequest.postId()).get())
                .user(authService.getCurrentUserEntity()).build();
        System.out.println(comment);
        CommentEntity savedComment = commentRepository.save(comment);
        System.out.println(savedComment);
        CommentResponse commentResponse = commentMapper.commentEntityToCommentResponse(savedComment);
        System.out.println(commentResponse);
        return commentResponse;
    }
    
}
