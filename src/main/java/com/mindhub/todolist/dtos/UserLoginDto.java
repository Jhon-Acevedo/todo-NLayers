package com.mindhub.todolist.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class UserLoginDto {
    @Setter
    @Getter
    private String username;
    @Setter
    @Getter
    private String password;
    private String email;

    public UserLoginDto() {
    }

    public UserLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginDto userDto = (UserLoginDto) o;
        return Objects.equals(username, userDto.username) && Objects.equals(password, userDto.password) && Objects.equals(email, userDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
