package com.example.data.impl;

import com.example.data.TaskListRepository;
import com.example.data.models.Task;
import com.example.exeption.MyException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskListRepositoryImpl implements TaskListRepository {

    private static Long nextTaskId = 1L;
    private static final List<Task> taskList = new ArrayList<>();

    @Override
    public void createTask(String description) {
        taskList.add(
                new Task()
                        .setId(nextTaskId++)
                        .setClosed(false)
                        .setDescription(description)
        );
    }

    @Override
    public List<Task> getAllTasks() {
        return taskList;
    }

    @Override
    public Task getTaskById(Long taskId) throws MyException {
        return taskList.stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new MyException("taskNotFound"));
    }

    @Override
    public List<Task> getTaskByDescription(String description) {
        return taskList.stream()
                .filter(t -> t.getDescription().contains(description))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getTaskByStatus(boolean isClosed) {
        return taskList.stream()
                .filter(t -> t.isClosed() == isClosed)
                .collect(Collectors.toList());
    }

    @Override
    public void updateTask(Task task) throws MyException {
        deleteTaskById(task.getId());
        taskList.add(task);
        taskList.sort(Comparator.comparing(Task::getId));
    }

    @Override
    public void deleteTaskById(Long taskId) throws MyException {
        taskList.remove(getTaskById(taskId));
    }
}