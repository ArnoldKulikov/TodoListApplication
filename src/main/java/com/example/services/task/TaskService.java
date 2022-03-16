package com.example.services.task;

import com.example.entities.Task;
import com.example.exeption.MyException;

import java.util.List;

public interface TaskService {

    Task createTask(String description);

    List<Task> getOpenTaskList();

    List<Task> getTaskList();

    List<Task> searchTask(String search);

    Task changeTask(Long taskId, Boolean closed, String description) throws MyException;

    void deleteTask(String taskId) throws MyException;

}
