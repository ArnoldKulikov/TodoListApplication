package com.example.services.task;

import com.example.exeption.MyException;

public interface TaskServiceProvider {

    Boolean checkTaskId(String taskId);

    void deleteTask(String taskId) throws MyException;

}
