package com.example.taskmanagement.controller;

import com.example.taskmanagement.dto.TaskDto;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("")
@AllArgsConstructor
public class TaskController{
    private TaskService taskService;

    @PostMapping(value="/task")
    public ResponseEntity<Void> createTask(@RequestBody TaskDto taskDto)
    {
        taskService.create(taskDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/task/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long taskId)
    {
        TaskDto task = taskService.getById(taskId);
        if(task != null)
            return ResponseEntity.status(HttpStatus.OK).body(task);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = "/tasks/{status}")
    public ResponseEntity<List<TaskDto>> getTaskByStatus(@PathVariable String status)
    {
        List<TaskDto> tasks = taskService.getByStatus(status);
        if(tasks.size() != 0)
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getByStatus(status));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping(value = "/task")
    public ResponseEntity<Void> update(@RequestBody TaskDto dto){
        Task task = taskService.update(dto);
        if(task!=null)
        {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping(value = "/task/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        taskService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
