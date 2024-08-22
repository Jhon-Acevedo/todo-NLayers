package com.mindhub.todolist.dtos.response;

import com.mindhub.todolist.models.UserEntity;

public record UserResponse (Long id, String name, String email, String role) {

    public static UserResponse toResponse(UserEntity userDto) {
        return new UserResponse(userDto.getId(), userDto.getUsername(), userDto.getEmail(), userDto.getRol().name());
    }
}
