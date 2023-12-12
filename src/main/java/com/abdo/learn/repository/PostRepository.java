package com.abdo.learn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdo.learn.model.entity.PostEntity;
import com.abdo.learn.model.entity.UserEntity;

import java.util.List;


@Repository
public interface PostRepository extends CrudRepository<PostEntity,Long>{
    List<PostEntity> findByUser(UserEntity user);
}
