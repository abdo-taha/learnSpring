package com.abdo.learn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.abdo.learn.model.dto.request.UserRegisterRequest;
import com.abdo.learn.model.dto.response.UserResponse;
import com.abdo.learn.model.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class); //singleton

    UserResponse userEntityToUserResponse(UserEntity user);

    @Mapping(target = "id", ignore = true)
    UserEntity UserRegisterRequestToUserEntity(UserRegisterRequest user);

    @Mapping(target = "password", ignore = true)
    UserEntity UserResponseToUserEntity(UserResponse user);
}
