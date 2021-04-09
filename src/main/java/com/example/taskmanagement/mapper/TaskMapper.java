package com.example.taskmanagement.mapper;


import com.example.taskmanagement.dto.TaskDto;
import com.example.taskmanagement.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "taskId", ignore = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "dueDate", source = "dueDate")
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "position", source = "position")
    Task mapToTask(TaskDto taskDto);

    @Mapping(target = "taskId", source="taskId")
    TaskDto mapToDto(Task task);
}
