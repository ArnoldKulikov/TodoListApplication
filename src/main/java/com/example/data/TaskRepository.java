package com.example.data;

import com.example.data.models.common.Task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByOrderByIdAsc();

    List<Task> findByClosedOrderByIdAsc(Boolean closed);

    List<Task> findByDescriptionContainingOrderByIdAsc(String description);
}
