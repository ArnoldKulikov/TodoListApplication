package com.example.services.task;

import com.example.exeption.MyException;
import org.springframework.stereotype.Service;

@Service
public class CommonTaskContext {
    private CommonTaskService deleteTask;

    public CommonTaskService setDeleteTaskMethod(CommonTaskService method) {
        return this.deleteTask = method;
    }

    public void executeDelete(String taskId) throws MyException {
        deleteTask.deleteTask(taskId);
    }
}
