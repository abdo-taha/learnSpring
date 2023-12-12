package com.abdo.learn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdo.learn.model.dto.request.CreatePostRequest;
import com.abdo.learn.model.dto.request.EditPostRequest;
import com.abdo.learn.model.dto.response.PostResponse;
import com.abdo.learn.service.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("api/v1/post")
@RequiredArgsConstructor
public class PostController {
    final private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostResponse> createPost(@RequestBody CreatePostRequest post) {
        return ResponseEntity.ok(postService.createPost(post));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse>getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.deletePostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> editPost(@PathVariable Long id, @RequestBody EditPostRequest post) {
        return ResponseEntity.ok(postService.editPost(post, id));
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<PostResponse>> getMethodName(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostsByUserId(id));
    }
    
    
    
}
