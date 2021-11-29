package com.example.data;

import com.example.data.models.common.TaskDto;
import com.example.exeption.MyException;

import java.util.List;

public interface TaskRepository {

    void createTask(TaskDto taskDto);

    List<TaskDto> getAllTasks();

    TaskDto getTaskById(Long taskId) throws MyException;

    List<TaskDto> search(String description);

    List<TaskDto> getTaskByStatus(boolean closed);

    void updateTask(TaskDto taskDto) throws MyException;

    void deleteTaskById(Long taskId) throws MyException;

}