package com.example.data.impl;

import com.example.data.TaskListRepository;
import com.example.data.models.TaskDto;
import com.example.exeption.MyException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskListRepositoryImpl implements TaskListRepository {

    private final List<TaskDto> taskDtoList = new ArrayList<TaskDto>();
    private Long nextTaskId = 1L;

    @Override
    public void createTask(TaskDto taskDto) {
        taskDtoList.add(taskDto.setId(nextTaskId++));
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskDtoList;
    }

    @Override
    public TaskDto getTaskById(Long taskId) throws MyException {
        return taskDtoList.stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new MyException("taskNotFound"));
    }

    @Override
    public List<TaskDto> getTaskByDescription(String description) {
        return taskDtoList.stream()
                .filter(t -> t.getDescription().contains(description))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getTaskByStatus(boolean isClosed) {
        return taskDtoList.stream()
                .filter(t -> t.isClosed() == isClosed)
                .collect(Collectors.toList());
    }

    @Override
    public void updateTask(TaskDto taskDto) {
        deleteTaskById(taskDto.getId());
        taskDtoList.add(taskDto);
        taskDtoList.sort(Comparator.comparing(TaskDto::getId));
    }

    @Override
    public void deleteTaskById(Long taskId) {
        try {
            taskDtoList.remove(getTaskById(taskId));
        } catch (MyException ignored) {
        }
    }
}
