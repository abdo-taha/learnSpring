package com.abdo.learn.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    
    @GetMapping("/")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("ok");
    } 
}
