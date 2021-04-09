package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.TaskDto;
import com.example.taskmanagement.mapper.TaskMapper;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Transactional
    public TaskDto create(TaskDto taskDto)
    {
        taskDto.setPosition(taskRepository.countAllByStatus(taskDto.getStatus()));
        Task task = taskRepository.save(taskMapper.mapToTask(taskDto));
        taskDto.setTaskId(task.getTaskId());
        return taskDto;
    }

    public TaskDto getById(Long id) {
        Task task = taskRepository.findByTaskId(id).orElse(null);
        return taskMapper.mapToDto(task);
    }

    public List<TaskDto> getByStatus(String status) {
        List<Task> tasks = taskRepository.findByStatusOrderByPosition(status);
        List<TaskDto> dtos = new ArrayList<>();
        for(Task task: tasks)
        {
            dtos.add(taskMapper.mapToDto(task));
        }
        return dtos;
    }

    @Transactional
    public Task update(TaskDto dto) {
        Task task = taskRepository.findByTaskId(dto.getTaskId()).orElse(null);
        if(task != null) {
            task.setTitle(dto.getTitle());
            task.setPriority(dto.getPriority());
            task.setDescription(dto.getDescription());
            task.setStatus(dto.getStatus());
            task.setDueDate(dto.getDueDate());
            task.setPosition(dto.getPosition());
            taskRepository.save(task);
        }
        return task;
    }

    @Transactional
    public void delete(Long id) {
        taskRepository.deleteByTaskId(id);
    }
}
