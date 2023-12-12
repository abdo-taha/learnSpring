package com.abdo.learn.controller;

import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.abdo.learn.service.AuthService;
// import com.abdo.learn.service.PhotoSaveService;

import lombok.RequiredArgsConstructor;

// import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestController {
    
    // final private AuthService authService;
    // final private PhotoSaveService photoSaveService;
    @GetMapping("/")
    public ResponseEntity<String> test() {
        //system.out.println(authService.getCurrentUser());
        return ResponseEntity.ok("ok");
    }
    
    // @PostMapping("/upload")
    // public String uploadPhoto(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
    //     // //system.out.println("controller");
    //     return photoSaveService.save(file, "test.jpg");
    // }
    
    // @GetMapping("/url/{name}")
    // public String getUrl(@PathVariable String name) {
    //     return photoSaveService.getURL(name);
    // }

    // @DeleteMapping("/{name}")
    // public Boolean deletePhoto(@PathVariable String name) {
    //     return photoSaveService.deletePhoto(name);
    // }
    

}
