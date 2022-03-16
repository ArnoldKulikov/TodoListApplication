package com.example.services.task.imp;

import com.example.exeption.MyException;
import com.example.services.task.CommonTaskService;
import com.example.services.task.TaskServiceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonTaskServiceImp implements CommonTaskService {

    private final List<TaskServiceProvider> list = new ArrayList<>();

    @Override
    public void deleteTask(String taskId) throws MyException {
        for (TaskServiceProvider taskServiceProvider : list) {
            if (taskServiceProvider.checkTaskId(taskId)) {
                taskServiceProvider.deleteTask(taskId);
                return;
            }
        }
    }
}
