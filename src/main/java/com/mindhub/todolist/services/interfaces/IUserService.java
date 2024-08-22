package com.mindhub.todolist.services.interfaces;

import com.mindhub.todolist.dtos.UserDto;
import com.mindhub.todolist.models.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    UserDto createUser(UserDto request);

    UserDto getUser(Long id) throws Exception;

    Optional<UserDto> updateUser(Long id, UserDto request);

    void deleteUser(Long id) throws Exception;

    UserEntity registerUser(UserDto registrationDto);

    List<UserDto> getAllUsers();
}
