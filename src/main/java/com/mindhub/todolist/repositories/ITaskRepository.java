package com.mindhub.todolist.repositories;

import com.mindhub.todolist.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITaskRepository extends JpaRepository<Task,Long> {
}
