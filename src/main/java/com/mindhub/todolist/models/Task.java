package com.mindhub.todolist.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindhub.todolist.models.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity users;

    public Task(String title, String description, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

}
