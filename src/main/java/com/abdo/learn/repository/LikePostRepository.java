package com.abdo.learn.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdo.learn.model.entity.LikePostEntity;
import com.abdo.learn.model.entity.PostEntity;
import com.abdo.learn.model.entity.UserEntity;

@Repository
public interface LikePostRepository extends CrudRepository<LikePostEntity, Long> {
    
    Optional<LikePostEntity> findByPostAndUser(PostEntity post, UserEntity user);
}
