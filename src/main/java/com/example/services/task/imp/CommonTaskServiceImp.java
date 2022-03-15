package com.example.services.task.imp;

import com.example.exeption.MyException;
import com.example.services.task.CommonTaskContext;
import com.example.services.task.CommonTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonTaskServiceImp implements CommonTaskService {
    private final CommonTaskContext commonTaskContext;
    private final ExtTaskServiceImp extTaskServiceImp;
    private final TaskServiceImp taskServiceImp;

    private List<CommonTaskService> list = new ArrayList<>();

    @Override
    public void deleteTask(String taskId) throws MyException {
        if (taskId.contains("EXT_")) list.add(extTaskServiceImp);
        if (taskId.contains("TDLA_")) list.add(taskServiceImp);
        for (CommonTaskService commonTaskService: list) {
            commonTaskContext.setDeleteTaskMethod(commonTaskService);
            commonTaskContext.executeDelete(taskId);
        }
    }
}
