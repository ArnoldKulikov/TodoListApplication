package com.example.services.task;

import com.example.entities.Task;
import com.example.entities.User;
import com.example.exeption.MyException;
import com.example.models.common.TaskList;

import java.util.List;
import java.util.concurrent.Future;

public interface TaskService {

    Task createTask(String description);

    List<Task> getOpenTaskList();

    Future<TaskList> getTaskList(User user);

    List<Task> searchTask(String search);

    Task changeTask(Long taskId, Boolean closed, String description) throws MyException;

    void deleteTask(String taskId) throws MyException;

}
