package com.abdo.learn.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdo.learn.model.entity.PhotoEntity;

@Repository
public interface PhotoRepository extends CrudRepository<PhotoEntity,UUID> {
    
}
