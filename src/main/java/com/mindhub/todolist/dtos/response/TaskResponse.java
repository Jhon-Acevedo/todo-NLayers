package com.mindhub.todolist.dtos.response;

import com.mindhub.todolist.models.enums.TaskStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class TaskResponse {

    private String title;
    private String description;
    private TaskStatus status;

    public TaskResponse(String title, String description, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskResponse that = (TaskResponse) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, status);
    }

    @Override
    public String toString() {
        return "TaskResponse{" +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}

