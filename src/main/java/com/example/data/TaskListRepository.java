package com.example.data;

import com.example.data.models.Task;
/*import com.example.exeption.MyException;*/

import java.util.List;

public interface TaskListRepository {

    void createTask(Task task);

    List<Task> getAllTasks();

/*    Task getTaskById(Long taskId) throws MyException;

    List<Task> getTaskByDescription(String description);*/

    List<Task> getTaskByStatus(boolean isClosed);

/*    void updateTask(Task task) throws MyException;

    void deleteTaskById(Long taskId) throws MyException;*/

}
