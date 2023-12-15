package com.abdo.learn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdo.learn.model.dto.request.LikePostRequest;
import com.abdo.learn.service.LikeService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api/v1/like")
@RequiredArgsConstructor
public class LikeController {
    final private LikeService likeService;

    @PostMapping
    public ResponseEntity<Boolean> createLikePost(@RequestBody LikePostRequest likePostRequest) {
        // System.out.println(likePostRequest);
        return ResponseEntity.ok().body( likeService.createLike(likePostRequest));
    }
    

    @DeleteMapping
    public ResponseEntity<Boolean> deleteLike(@RequestBody LikePostRequest likePostRequest) {
        
        return ResponseEntity.ok().body(likeService.removeLike(likePostRequest));
    }


    @GetMapping
    public ResponseEntity<Boolean> isLiked(@RequestBody LikePostRequest likePostRequest) {
        

        return ResponseEntity.ok().body(likeService.isLiked(likePostRequest));
    }
    
}
