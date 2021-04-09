package com.example.taskmanagement.model;

import lombok.*;
import org.hibernate.annotations.Check;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

//getter and setter
@Data
//table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Check(constraints = "priority IN ('LOW','MEDIUM','HIGH') AND status in ('todo', 'doing', 'done')")
public class Task {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="taskId")
    private Long taskId;
    @Column(name="position")
    private Long position;
    @NotEmpty
    @Column(name="title", unique=true)
    private String title;
    @Nullable
    @Lob
    @Column(name="description")
    private String description;
    @Column(name="dueDate")
    private String dueDate;
    @Column(name = "priority")
    private String priority;
    @Column(name = "status")
    private String status;
}
