package com.ToDoList.JPAGestionTareas.service;

import com.ToDoList.JPAGestionTareas.model.Task;

import java.util.List;

public interface ItaskService {

    //Métodos: ,  ,

    public List<Task> getAllTaks();
    public Task getTaskById(Long id);
    public Task createTask(Task task);
    void updateTask(Long id, Task taskDetails);
    public void deleteTask(Long id);
}
