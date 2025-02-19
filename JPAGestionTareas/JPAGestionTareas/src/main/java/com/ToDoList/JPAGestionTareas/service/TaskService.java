package com.ToDoList.JPAGestionTareas.service;

import com.ToDoList.JPAGestionTareas.model.Task;
import com.ToDoList.JPAGestionTareas.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ItaskService {
    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public List<Task> getAllTaks() {
        List<Task> taskList = taskRepository.findAll();
        return taskList;
    }

    @Override
    public Task getTaskById(Long id) {
        Task taskExistente = taskRepository.findById(id).orElse(null);
        return taskExistente;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void updateTask(Long id, Task taskDetails) {
        Task taskExistente = taskRepository.findById(id).orElse(null);

        if (taskExistente != null) {
            taskExistente.setTitle(taskDetails.getTitle());
            taskExistente.setDescripcion(taskDetails.getDescripcion());
            taskExistente.setCompleted(taskDetails.isCompleted());
            taskExistente.setDueDate(taskDetails.getDueDate());

            // Guardar cambios
            taskRepository.save(taskExistente);
        } else {
            System.out.println("La tarea no se encontró con el ID: " + id);
        }
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
