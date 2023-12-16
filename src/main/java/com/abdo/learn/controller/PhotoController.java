package com.abdo.learn.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abdo.learn.mapper.UserMapper;
import com.abdo.learn.model.entity.PhotoEntity;
import com.abdo.learn.model.entity.UserEntity;
import com.abdo.learn.service.AuthService;
import com.abdo.learn.service.PhotoDBService;
import com.abdo.learn.service.PhotoSaveService;
import com.abdo.learn.service.UsersService;
import com.abdo.learn.utils.PhotosUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/photo/")
@RequiredArgsConstructor
public class PhotoController {
    final private AuthService authService;
    final private PhotoSaveService photoSaveService;
    final private PhotoDBService photoDBService;
    final private PhotosUtils photosUtils;
    final private UserMapper userMapper;
    final private UsersService usersService;

    @PostMapping("/upload")
    public String uploadPhoto(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
        UserEntity user = authService.getCurrentUserEntity();
        String ext = photosUtils.getExtensionByStringHandling(file.getOriginalFilename());
        String photoName = photoDBService.savePhotoToDB(user, ext);
        photoName += "." + ext;
        return photoSaveService.save(file, photoName);
    }

    @DeleteMapping("/{name}")
    public Boolean deletePhoto(@PathVariable String name) {
        // system.out.println(name + " name in controller");
        UserEntity user = authService.getCurrentUserEntity();
        PhotoEntity photo = photoDBService.getPhotoByName(name);
        // system.out.println(photo + " photo from controller");
        if (photo.getOwner().getId() != user.getId()) {
            return false;
        }
        return photoSaveService.deletePhoto(name) & photoDBService.deletePhoto(name);
    }

    @GetMapping("/{id}/photos")
    public List<String> getAllPhotos(@PathVariable Long id) {
        UserEntity user = userMapper.UserResponseToUserEntity(usersService.getUser(id));
        return photoDBService.getAllPhotosOfUser(user);
    }
}
