package com.example.services.task;

import com.example.entities.Task;
import com.example.entities.User;
import com.example.exeption.MyException;
import com.example.models.common.TaskDto;

import java.util.List;
import java.util.concurrent.Future;

public interface TaskService {

    Task createTask(String description);

    Future<List<TaskDto>> getOpenTaskList(User user);

    Future<List<TaskDto>> getTaskList(User user);

    List<Task> searchTask(String search);

    Task changeTask(Long taskId, Boolean closed, String description) throws MyException;

    void deleteTask(String taskId) throws MyException;

}
