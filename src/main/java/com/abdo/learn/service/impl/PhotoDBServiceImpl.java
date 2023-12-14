package com.abdo.learn.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.abdo.learn.model.entity.PhotoEntity;
import com.abdo.learn.model.entity.UserEntity;
import com.abdo.learn.repository.PhotoRepository;
import com.abdo.learn.service.PhotoDBService;
import com.abdo.learn.service.PhotoSaveService;
import com.abdo.learn.utils.PhotosUtils;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class PhotoDBServiceImpl implements PhotoDBService {

    final private PhotoRepository photoRepository;
    final private PhotosUtils photosUtils;
    final private PhotoSaveService photoSaveService;
    @Override
    public String savePhotoToDB(UserEntity user,String ext) {
        PhotoEntity photo = PhotoEntity.builder().owner(user).ext(ext).build();
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

    @Override
    public List<String> getAllPhotosOfUser(UserEntity user) {
        return photoRepository.findByOwner(user).stream().map((photo)->photoSaveService.getURL(photo.getId().toString()+"."+photo.getExt())).toList();
    }

    @Override
    public PhotoEntity getPhotoById(UUID id) {
        return photoRepository.findById(id).get();
    }
    
}
