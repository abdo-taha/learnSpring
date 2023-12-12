package com.abdo.learn.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoSaveService {
    
    String save(MultipartFile photo,String photoName) throws IllegalStateException, IOException;
    
    String getURL(String photoName);

    Boolean deletePhoto(String photoName);
}
