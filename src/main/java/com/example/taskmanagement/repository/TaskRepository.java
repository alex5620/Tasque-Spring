package com.example.taskmanagement.repository;

import com.example.taskmanagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatusOrderByPosition(String status);
    Optional<Task> findByTaskId(Long id);
    Long countAllByStatus(String status);
    void deleteByTaskId(Long taskId);
}
