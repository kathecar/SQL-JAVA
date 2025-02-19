package com.ToDoList.JPAGestionTareas.repository;

import com.ToDoList.JPAGestionTareas.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {
}
