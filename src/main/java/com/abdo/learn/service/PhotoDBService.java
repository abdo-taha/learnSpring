package com.abdo.learn.service;

import java.util.List;

import com.abdo.learn.model.entity.PhotoEntity;
import com.abdo.learn.model.entity.UserEntity;

public interface PhotoDBService {
    
    String savePhotoToDB(UserEntity user,String ext);

    Boolean deletePhoto(String name);

    PhotoEntity getPhotoByName(String name);

    List<String> getAllPhotosOfUser(UserEntity user);
}
