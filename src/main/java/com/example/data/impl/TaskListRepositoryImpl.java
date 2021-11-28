package com.example.data.impl;

import com.example.data.TaskListRepository;
import com.example.data.models.common.TaskDto;
import com.example.exeption.MyException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskListRepositoryImpl implements TaskListRepository {

    private final List<TaskDto> taskListRepositoryImpl = new ArrayList<TaskDto>();
    private Long nextTaskId = 1L;

    @Override
    public void createTask(TaskDto taskDto) {
        taskListRepositoryImpl.add(taskDto.setId(nextTaskId++));
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskListRepositoryImpl;
    }

    @Override
    public TaskDto getTaskById(Long taskId) throws MyException {
        return taskListRepositoryImpl.stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new MyException("taskNotFound"));
    }

    @Override
    public List<TaskDto> getTaskByDescription(String description) {
        return (ArrayList<TaskDto>) taskListRepositoryImpl.stream()
                .filter(t -> t.getDescription().contains(description))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getTaskByStatus(boolean closed) {
        return (ArrayList<TaskDto>) taskListRepositoryImpl.stream()
                .filter(t -> t.isClosed() == closed)
                .collect(Collectors.toList());
    }

    @Override
    public void updateTask(TaskDto taskDto) throws MyException {
        deleteTaskById(taskDto.getId());
        taskListRepositoryImpl.add(taskDto);
        taskListRepositoryImpl.sort(Comparator.comparing(TaskDto::getId));
    }

    @Override
    public void deleteTaskById(Long taskId) throws MyException {
            taskListRepositoryImpl.remove(getTaskById(taskId));

    }
}
