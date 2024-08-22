package com.mindhub.todolist.dtos;

import com.mindhub.todolist.models.enums.RolEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {

    private String username;
    private String password;
    private String email;
    private RolEnum rol;

    public UserDto(String username, String password, String email, RolEnum rol) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
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
