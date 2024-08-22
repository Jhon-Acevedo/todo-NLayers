package com.mindhub.todolist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindhub.todolist.controller.common.WithMockUser;
import com.mindhub.todolist.dtos.UserDto;
import com.mindhub.todolist.models.enums.RolEnum;
import com.mindhub.todolist.services.interfaces.IUserService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AdminControllerWithSpringSecurityTest {

    @Autowired
    private MockMvc api;

    @MockBean
    private IUserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void AdminController_updateUser_ReturnNoAuthorized() throws Exception {
        long userId = 1L;
        UserDto updatedUserDto = new UserDto("pedroUpdated", "passwordUpdated", "emailUpdated@gmail.com", RolEnum.ADMIN);

        when(userService.updateUser(userId, updatedUserDto)).thenReturn(Optional.of(updatedUserDto));

        ResultActions response = api.perform(put("/api/admin/update/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUserDto)));

        response.andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    public void AdminController_updateUser_ReturnUserUpdated() throws Exception {
        long userId = 1L;
        UserDto updatedUserDto = new UserDto("pedroUpdated", "passwordUpdated", "email@gmail.com", RolEnum.ADMIN);

        when(userService.updateUser(userId, updatedUserDto)).thenReturn(Optional.of(updatedUserDto));

        ResultActions response = api.perform(put("/api/admin/update/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUserDto)));

        response.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is(updatedUserDto.getUsername())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(updatedUserDto.getEmail()))
                );
    }

    @Test
    @WithMockUser
    public void AdminController_UpdateUser_ReturnUserNotFound() throws Exception {
        long userId = 1L;
        UserDto updatedUserDto = new UserDto("userPedro", "password", "pedro@gmail.com", RolEnum.USER);

        when(userService.updateUser(userId, updatedUserDto)).thenReturn(Optional.empty());

        ResultActions response = api.perform(put("/api/admin/update/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUserDto)));

        response.andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void AdminController_deleteUser_ReturnNoContent() throws Exception {
        long userId = 1L;
        doNothing().when(userService).deleteUser(userId);

        ResultActions response = api.perform(delete("/api/admin/deleteUser/{id}", userId));

        response.andExpect(status().isNoContent());
    }

}
