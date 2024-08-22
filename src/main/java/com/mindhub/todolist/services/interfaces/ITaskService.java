package com.mindhub.todolist.services.interfaces;

import com.mindhub.todolist.dtos.request.TaskRequest;
import com.mindhub.todolist.dtos.response.TaskResponse;
import com.mindhub.todolist.models.Task;

import java.util.List;

public interface ITaskService {
    public TaskResponse createTask(TaskRequest request) throws Exception;
    public TaskResponse getTask(Long id) throws Exception;
    public TaskResponse updateTask(Long id, TaskRequest request) throws Exception;
    public String DeleteTask(Long id) throws Exception;
    public List<Task> getAllTasks() throws Exception;
}
