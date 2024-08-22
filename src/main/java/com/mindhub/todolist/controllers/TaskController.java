package com.mindhub.todolist.controllers;

import com.mindhub.todolist.dtos.request.TaskRequest;
import com.mindhub.todolist.dtos.response.TaskResponse;
import com.mindhub.todolist.services.interfaces.ITaskService;
import com.mindhub.todolist.models.Task;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@Tag(name = "Task", description = "API to manage tasks of the user")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class TaskController {

    private ITaskService taskService;

    @PostMapping("/createTask")
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request) throws Exception {
        try {
            TaskResponse response = taskService.createTask(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getTask/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable("id") Long id) {
        try {
            TaskResponse response = taskService.getTask(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateTask/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable("id") Long id, @RequestBody TaskRequest request) throws Exception {
        try {
            TaskResponse response = taskService.updateTask(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() throws Exception {
        try {
            return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
