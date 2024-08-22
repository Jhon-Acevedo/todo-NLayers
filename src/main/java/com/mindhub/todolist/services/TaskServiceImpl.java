package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.request.TaskRequest;
import com.mindhub.todolist.dtos.response.TaskResponse;
import com.mindhub.todolist.services.interfaces.ITaskService;
import com.mindhub.todolist.models.Task;
import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.models.enums.TaskStatus;
import com.mindhub.todolist.repositories.ITaskRepository;
import com.mindhub.todolist.repositories.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements ITaskService {

    private ITaskRepository taskRepository;
    private IUserRepository userRepository;

    @Override
    public TaskResponse createTask(TaskRequest request) throws Exception {
        if (request.getStatus() != TaskStatus.PENDING
                && request.getStatus() != TaskStatus.IN_PROGRESS
                && request.getStatus() != TaskStatus.COMPLETED) {
            throw new Exception("El estado asignado no corresponde a los establecidos");
        }
        Task newTask = new Task();
        newTask.setDescription(request.getDescription());
        newTask.setStatus(request.getStatus());
        newTask.setTitle(request.getTitle());
        UserEntity findUserById = userRepository.findById(request.getUserId()).orElse(null);
        if (findUserById == null) throw new Exception("No existe usuario!!");
        findUserById.addTask(newTask);
        taskRepository.save(newTask);

        return new TaskResponse(newTask.getTitle(), newTask.getDescription(), newTask.getStatus());
    }

    @Override
    public TaskResponse getTask(Long id) throws Exception {
        Task findTaskById = taskRepository.findById(id).orElse(null);
        if (findTaskById == null) {
            throw new Exception("No existe registro de esta tarea en base de datos");
        }
        return new TaskResponse(findTaskById.getTitle(),
                findTaskById.getDescription(), findTaskById.getStatus());
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest request) throws Exception {
        Optional<Task> findTaskById = taskRepository.findById(id);
        if (findTaskById.isEmpty()) {
            throw new Exception("No existe registro de esta tarea en base de datos");
        }
        UserEntity findUser = userRepository.findById(request.getUserId()).orElse(null);
        if (findUser == null) throw new Exception("No existe usuario!!");
        Task updateATask = findTaskById.get();
        updateATask.setTitle(request.getTitle());
        updateATask.setDescription(request.getDescription());
        updateATask.setUsers(findUser);
        findUser.addTask(updateATask);
        taskRepository.save(updateATask);

        return new TaskResponse(updateATask.getTitle(), updateATask.getDescription()
                , updateATask.getStatus());
    }

    @Override
    public String DeleteTask(Long id) throws Exception {
        Optional<Task> findTaskById = taskRepository.findById(id);
        if (findTaskById.isEmpty()) {
            throw new Exception("No existe registro de esta tarea en base de datos");
        }
        taskRepository.delete(findTaskById.get());
        return "Eliminación de tarea exitosa";
    }

    @Override
    public List<Task> getAllTasks() throws Exception {
        return taskRepository.findAll();
    }


}
