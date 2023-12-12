package com.abdo.learn.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.abdo.learn.model.entity.PhotoEntity;
import com.abdo.learn.model.entity.UserEntity;
import com.abdo.learn.repository.PhotoRepository;
import com.abdo.learn.service.PhotoDBService;
import com.abdo.learn.utils.PhotosUtils;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class PhotoDBServiceImpl implements PhotoDBService {

    final private PhotoRepository photoRepository;
    final private PhotosUtils photosUtils;
    @Override
    public String savePhotoToDB(UserEntity user) {
        PhotoEntity photo = PhotoEntity.builder().owner(user).build();
        PhotoEntity savedPhoto = photoRepository.save(photo);
        //system.out.println(savedPhoto);
        return savedPhoto.getId().toString();
    }

    @Override
    public Boolean deletePhoto(String name) {
        Integer extensionLength = photosUtils.getExtensionByStringHandling(name).length()+1;
        name = name.substring(0, name.length() - extensionLength);
        UUID id = UUID.fromString(name);
        PhotoEntity photo = photoRepository.findById(id).get();
        photoRepository.delete(photo);
        return true;
    }

    @Override
    public PhotoEntity getPhotoByName(String name) {
        Integer extensionLength = photosUtils.getExtensionByStringHandling(name).length()+1;
        name = name.substring(0, name.length() - extensionLength);
        UUID id = UUID.fromString(name);
        return  photoRepository.findById(id).get();
    }
    
}
