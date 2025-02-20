package com.ToDoList.JPAGestionTareas.controller;

import com.ToDoList.JPAGestionTareas.model.Task;
import com.ToDoList.JPAGestionTareas.service.ItaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private ItaskService taskServ;

    @GetMapping("/getAll")
    public List<Task> getAllTasks() {
        return taskServ.getAllTaks();
    }
    @GetMapping("/getTaskById/{id}")
    public Task encontrarId(@PathVariable Long id) {
        return taskServ.getTaskById(id);
    }
    @PostMapping("/createTask")
    public String createTask(@RequestBody Task task) {
        try {
            taskServ.createTask(task);
            return "La tarea fue creada con éxito";
        } catch (Exception e) {
            return "Error al crear la tarea: " + e.getMessage();
        }
    }
    @PutMapping ("/updateTask/{id}")
    public ResponseEntity<String> updateTask(
            @PathVariable Long id,
            @RequestBody Task taskDetails){
        taskServ.updateTask(id, taskDetails);
        return ResponseEntity.ok("Se actualizó correctamente");
    }

    @DeleteMapping ("/deleteTask/{id}")
// coloco las llaves en algo que voy cambienado
    public String deleteTask(@PathVariable Long id){
        taskServ.deleteTask(id);
        return "La tarea se elimino con exito";
    }


}


