package com.example.data.impl;

import com.example.data.TaskRepository;
import com.example.data.models.common.TaskDto;
import com.example.exeption.MyException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskRepositoryImpl implements TaskRepository {

    private final List<TaskDto> data = new ArrayList<TaskDto>();
    private Long nextTaskId = 1L;

    @Override
    public void createTask(TaskDto taskDto) {
        data.add(taskDto.setId(nextTaskId++));
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return Collections.unmodifiableList(data);
    }

    @Override
    public TaskDto getTaskById(Long taskId) throws MyException {
        return data.stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new MyException("taskNotFound"));
    }

    @Override
    public List<TaskDto> search(String description) {
        return data.stream()
                .filter(t -> t.getDescription().contains(description))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getTaskByStatus(boolean closed) {
        return data.stream()
                .filter(t -> t.isClosed() == closed)
                .collect(Collectors.toList());
    }

    @Override
    public void updateTask(TaskDto taskDto) throws MyException {
        deleteTaskById(taskDto.getId());
        data.add(taskDto);
        data.sort(Comparator.comparing(TaskDto::getId));
    }

    @Override
    public void deleteTaskById(Long taskId) throws MyException {
            data.remove(getTaskById(taskId));

    }
}
