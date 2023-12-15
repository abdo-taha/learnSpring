package com.abdo.learn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdo.learn.model.dto.request.CommentRequest;
import com.abdo.learn.model.dto.response.CommentResponse;
import com.abdo.learn.service.CommentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    final private CommentService commentService;
    @PostMapping("")
    public CommentResponse postMethodName(@RequestBody CommentRequest commentRequest) {
        System.out.println(commentRequest);
        return commentService.createComment(commentRequest);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long id) {
        return ResponseEntity.ok().body(commentService.deleteComment(id));
    }
}
