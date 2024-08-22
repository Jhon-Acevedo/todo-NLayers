package com.mindhub.todolist.models.exceptions.user;

public class UserExistException extends RuntimeException {
    public UserExistException(String message) {
        super(message);
    }
}
