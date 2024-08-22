package com.mindhub.todolist.models;

import com.mindhub.todolist.models.enums.RolEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    @Column(unique = true)
    private String username;
    @Setter
    @Getter
    private String password;
    @Setter
    @Getter
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Task> tasks = new HashSet<>();

    @Setter
    @Getter
    private RolEnum rol = RolEnum.USER;


    public UserEntity(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserEntity(String username, String password, String email, RolEnum rol) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
    }

    public Set<Task> getTask() {
        return tasks;
    }

    public void addTask(Task task) {
        task.setUsers(this);
        tasks.add(task);
    }
}
