package com.example.taskmanagement.dto;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private Long taskId;
    private Long position;
    private String title;
    private String description;
    private String dueDate;
    private String priority;
    private String status;
}
