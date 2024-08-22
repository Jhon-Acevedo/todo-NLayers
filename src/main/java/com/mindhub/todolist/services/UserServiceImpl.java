package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.UserDto;
import com.mindhub.todolist.models.exceptions.user.UserExistException;
import com.mindhub.todolist.models.exceptions.user.UserNotFoundException;
import com.mindhub.todolist.services.interfaces.IUserService;
import com.mindhub.todolist.services.mappers.UserMapper;
import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.repositories.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto request) {
        UserEntity createAUser = UserMapper.userDtoToUser(request);
        createAUser = userRepository.save(createAUser);
        return UserMapper.userToUserDto(createAUser);
    }

    @Override
    public UserDto getUser(Long id) throws Exception {
        UserEntity getAUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("User with id %s not found", id)));
        return UserMapper.userToUserDto(getAUser);
    }

    @Override
    public Optional<UserDto> updateUser(Long id, UserDto request) {
        UserEntity updateAUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("User with id %s not found", id)));

        updateAUser.setUsername(request.getUsername());
        updateAUser.setEmail(request.getEmail());
        updateAUser.setRol(request.getRol());
        updateAUser.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(updateAUser);

        return Optional.of(UserMapper.userToUserDto(updateAUser));
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        UserEntity FindUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("User with id %s not found", id)));
        userRepository.delete(FindUser);
    }

    @Override
    public UserEntity registerUser(UserDto registrationDto) {
        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent())
            throw new UserExistException("Email already exists");
        if (userRepository.findByUsername(registrationDto.getUsername()).isPresent())
            throw new UserExistException("Username already exists");

        String passwordEncoded = passwordEncoder.encode(registrationDto.getPassword());
        return userRepository.save(UserMapper.createUserEntity(registrationDto, passwordEncoded));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserMapper.usersToUsersDto(userRepository.findAll());
    }
}
