package com.abdo.learn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdo.learn.model.dto.request.CommentRequest;
import com.abdo.learn.model.dto.response.CommentResponse;
import com.abdo.learn.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/comment")
@RequiredArgsConstructor
@Tag(name = "Comments")
public class CommentController {
    final private CommentService commentService;

    @Operation(description = "create", summary = "creating new comment")
    @PostMapping("")
    public CommentResponse postMethodName(@Valid @RequestBody CommentRequest commentRequest) {
        System.out.println(commentRequest);
        return commentService.createComment(commentRequest);
    }

    @Operation(description = "delete", summary = "delete a comment")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteComment(@Valid @PathVariable Long id) {
        return ResponseEntity.ok().body(commentService.deleteComment(id));
    }
}
