package com.example.data.impl;

import com.example.data.TaskListRepository;
import com.example.data.models.Task;
import com.example.exeption.MyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskListRepositoryImpl implements TaskListRepository {

    private static final List<Task> taskList = new ArrayList<>();

    @Override
    public void createTask(Task task) {
        taskList.add(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(taskList);
    }

    @Override
    public Task getTaskById(Long taskId) throws MyException {
        Optional<Task> result = taskList.stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst();

        if (result.isPresent()) {
            return result.get();
        }
        throw new MyException("taskNotFound");
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
                .filter((t -> t.isClosed() == isClosed))
                .collect(Collectors.toList());
    }

    @Override
    public void updateTask(Task task) throws MyException {
        Task localTask = getTaskById(task.getId());

        localTask.setId(task.getId());
        localTask.setDescription(task.getDescription());
        localTask.setClosed(task.isClosed());
    }

    @Override
    public void deleteTaskById(Long taskId) throws MyException {
        taskList.remove(getTaskById(taskId));
    }
}