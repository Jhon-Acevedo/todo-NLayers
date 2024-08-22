package com.mindhub.todolist.controllers;

import com.mindhub.todolist.models.exceptions.task.TaskNotFoundException;
import com.mindhub.todolist.models.exceptions.user.UserExistException;
import com.mindhub.todolist.models.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<?> handleTaskNotFoundException(TaskNotFoundException taskNotFoundException) {
        return new ResponseEntity<>(taskNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<?> handleUserExistException(UserExistException userExistException) {
        return new ResponseEntity<>(userExistException.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
