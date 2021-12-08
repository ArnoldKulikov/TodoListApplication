package com.example.data;

import com.example.data.models.common.TaskDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskDto, Long> {

    List<TaskDto> findAllByOrderByIdAsc();

    List<TaskDto> findByClosedOrderByIdAsc(Boolean closed);

    List<TaskDto> findByDescriptionContainingOrderByIdAsc(String description);
}
