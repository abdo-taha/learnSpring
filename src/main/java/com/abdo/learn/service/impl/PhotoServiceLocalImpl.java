package com.abdo.learn.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abdo.learn.service.PhotoSaveService;


@Service
public class PhotoServiceLocalImpl  implements PhotoSaveService{
    //TODO edit local path and make it in properties
    //TODO edit global path get it from somwhere
    String localImagesPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\"; 
    String globalImagePath = "localhost:8080/data/static/images/";
    @Override
    public String save(MultipartFile photo, String photoName) throws IllegalStateException, IOException {
        //system.out.println("save method");
        File directory = new File(localImagesPath);
        if (!directory.exists())
            directory.mkdirs();
        String path = localImagesPath +  photoName;
        
        photo.transferTo(new File(path));
        return getURL(photoName);
        
    }

    @Override
    public String getURL(String photoName) {
        return globalImagePath+photoName;
    }

    @Override
    public Boolean deletePhoto(String photoName) {
        String path = localImagesPath + photoName;
        //system.out.println(path + " path in local service");
        File file = new File(path);
        return file.delete();
    }
    
}
