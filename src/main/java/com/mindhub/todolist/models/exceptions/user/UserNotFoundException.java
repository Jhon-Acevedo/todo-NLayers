package com.mindhub.todolist.models.exceptions.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
