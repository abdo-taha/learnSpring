package com.abdo.learn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.abdo.learn.model.dto.request.CreatePostRequest;
import com.abdo.learn.model.dto.response.PostResponse;
import com.abdo.learn.model.entity.PostEntity;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "createdAt" , ignore = true)
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "user" , ignore = true)
    PostEntity PostRequestToPostEntity(CreatePostRequest post);


    PostResponse PostEntityToPostResponse(PostEntity post);

    // @Mapping(target = "createdAt", ignore = true)
    // @Mapping(target = "user",ignore = true)
    // PostEntity EditPostRequestToPostEntity(EditPostRequest post);
}
