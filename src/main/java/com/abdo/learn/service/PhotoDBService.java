package com.abdo.learn.service;

import com.abdo.learn.model.entity.PhotoEntity;
import com.abdo.learn.model.entity.UserEntity;

public interface PhotoDBService {
    
    String savePhotoToDB(UserEntity user);

    Boolean deletePhoto(String name);

    PhotoEntity getPhotoByName(String name);
}
