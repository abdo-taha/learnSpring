package com.abdo.learn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdo.learn.model.dto.request.CreatePostRequest;
import com.abdo.learn.model.dto.request.EditPostRequest;
import com.abdo.learn.model.dto.response.PostResponse;
import com.abdo.learn.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "post")
@RestController
@RequestMapping("api/v1/post")
@RequiredArgsConstructor
public class PostController {
    final private PostService postService;

    @Operation(description = "create new post")
    @PostMapping("/create")
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody CreatePostRequest post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    @Operation(description = "show a post")
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @Operation(description = "delete a post")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.deletePostById(id));
    }

    @Operation(description = "edit a post")
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> editPost(@PathVariable Long id,
            @Valid @RequestBody EditPostRequest post) {
        return ResponseEntity.ok(postService.editPost(post, id));
    }

    @Operation(description = "get all posts of a user")
    @GetMapping("/{id}/posts")
    public ResponseEntity<List<PostResponse>> getMethodName(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostsByUserId(id));
    }

}
