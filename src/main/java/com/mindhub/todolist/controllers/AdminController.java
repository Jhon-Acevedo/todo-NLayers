package com.mindhub.todolist.controllers;

import com.mindhub.todolist.dtos.UserDto;
import com.mindhub.todolist.services.interfaces.ITaskService;
import com.mindhub.todolist.services.interfaces.IUserService;
import com.mindhub.todolist.models.Task;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin", description = "API to manage users and tasks")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController {

    private IUserService userService;
    private ITaskService taskService;

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto request) throws Exception {
        try {
            UserDto response = userService.createUser(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) throws Exception {
        try {
            UserDto response = userService.getUser(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto request) throws Exception {
        try {
            Optional<UserDto> response = userService.updateUser(id, request);
            return response.map(userDto -> new ResponseEntity<>(userDto, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) throws Exception {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) throws Exception {
        try {
            String response = taskService.DeleteTask(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() throws Exception {
        try {
            return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() throws Exception {
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
