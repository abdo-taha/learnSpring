package com.abdo.learn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.abdo.learn.model.dto.response.CommentResponse;
import com.abdo.learn.model.entity.CommentEntity;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    CommentResponse commentEntityToCommentResponse(CommentEntity comment);
} 
