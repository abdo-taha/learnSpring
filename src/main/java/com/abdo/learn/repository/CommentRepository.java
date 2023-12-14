package com.abdo.learn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdo.learn.model.entity.CommentEntity;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity,Long>{
    
}
