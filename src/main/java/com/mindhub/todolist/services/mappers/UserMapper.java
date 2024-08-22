package com.mindhub.todolist.services.mappers;

import com.mindhub.todolist.dtos.UserDto;
import com.mindhub.todolist.models.UserEntity;

import java.util.List;

public class UserMapper {

    public static UserEntity userDtoToUser(UserDto userDto) {
        return new UserEntity(userDto.getUsername(), userDto.getPassword(), userDto.getEmail());
    }

    public static UserEntity createUserEntity(UserDto userDto, String passwordEncoded) {
        return new UserEntity(userDto.getUsername(), passwordEncoded, userDto.getEmail(), userDto.getRol());
    }

    public static UserDto userToUserDto(UserEntity userEntity) {
        return new UserDto(userEntity.getUsername(), userEntity.getPassword(), userEntity.getEmail(), userEntity.getRol());
    }

    public static List<UserDto> usersToUsersDto(List<UserEntity> users) {
        return users.stream().map(UserMapper::userToUserDto).toList();
    }
}
