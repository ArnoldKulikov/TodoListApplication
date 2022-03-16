package com.example.services.task.imp;

import com.example.exeption.MyException;
import com.example.facade.GetExtTaskListFacade;
import com.example.models.common.ExtTaskDto;
import com.example.services.task.ExtTaskService;
import com.example.services.task.TaskServiceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExtTaskServiceImp implements ExtTaskService, TaskServiceProvider {

    private final GetExtTaskListFacade getExtTaskListFacade;

    @Override
    public List<ExtTaskDto> getTaskList() {
        return getExtTaskListFacade.getExtTaskList(false);
    }

    @Override
    public List<ExtTaskDto> getAllTaskList() {
        return getExtTaskListFacade.getExtTaskList(true);
    }

    @Override
    public Boolean checkTaskId(String taskId) {
        return taskId.contains("EXT_");
    }

    @Override
    public void deleteTask(String taskId) throws MyException {
        getExtTaskListFacade.deleteExtTask(taskId.substring(4));
    }

}
