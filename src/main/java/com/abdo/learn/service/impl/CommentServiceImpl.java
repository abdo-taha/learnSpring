package com.abdo.learn.service.impl;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.abdo.learn.exception.NotFoundException;
import com.abdo.learn.exception.UnAuthorizedOperationException;
import com.abdo.learn.mapper.CommentMapper;
import com.abdo.learn.model.dto.request.CommentRequest;
import com.abdo.learn.model.dto.response.CommentResponse;
import com.abdo.learn.model.entity.CommentEntity;
import com.abdo.learn.model.entity.PostEntity;
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
            throw new UnAuthorizedOperationException(HttpStatus.UNAUTHORIZED, "You can't do this operation");
        commentRepository.deleteById(id);
        return true;
    }

    @Override
    public CommentResponse createComment(CommentRequest commentRequest) {
        PostEntity post = postRepository.findById(commentRequest.postId())
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND, "post not found"));
        CommentEntity comment = CommentEntity.builder()
                .content(commentRequest.content())
                .createdAt(LocalDateTime.now())
                .post(post)
                .user(authService.getCurrentUserEntity()).build();
        CommentEntity savedComment = commentRepository.save(comment);
        CommentResponse commentResponse = commentMapper.commentEntityToCommentResponse(savedComment);
        return commentResponse;
    }

}
