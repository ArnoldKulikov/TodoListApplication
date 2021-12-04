package com.example.data;

import com.example.data.models.common.TaskDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskDto, Long> {

    List<TaskDto> findByClosed(Boolean Closed, Sort sort);

    List<TaskDto> findByDescription(String Description, Sort sort);
}
