package com.mindhub.todolist.service;

import com.mindhub.todolist.dtos.UserDto;
import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.models.enums.RolEnum;
import com.mindhub.todolist.models.exceptions.user.UserNotFoundException;
import com.mindhub.todolist.repositories.IUserRepository;
import com.mindhub.todolist.services.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void UserService_UpdateUser_ReturnUserDto() throws Exception {
        long userId = 1L;
        UserEntity userEntity = new UserEntity("username", "password", "email@gmail.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        Optional<UserDto> userDto = userService.updateUser(userId, new UserDto("username2", "password", "email@gmail.com", RolEnum.USER));

        assertAll(() -> {
            Assertions.assertTrue(userDto.isPresent());
            Assertions.assertEquals("username2", userDto.get().getUsername());
            Assertions.assertEquals("encodedPassword", userDto.get().getPassword());
            Assertions.assertEquals("email@gmail.com", userDto.get().getEmail());
            Assertions.assertEquals(RolEnum.USER, userDto.get().getRol());
        });
    }

    @Test
    public void UserService_DeleteUser_ReturnVoid() {
        long userId = 1L;
        UserEntity userEntity = new UserEntity("username", "password", "email@gmail.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        doNothing().when(userRepository).delete(userEntity);

        assertAll(() -> userService.deleteUser(userId));
    }

    @Test
    public void UserService_DeleteUser_ReturnNotFoundException() {
        long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));
    }

}
