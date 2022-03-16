package com.example.services.task;

import com.example.exeption.MyException;

public interface CommonTaskService {

    void deleteTask(String taskId) throws MyException;

}
