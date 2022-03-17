package com.example.services.task.imp;

import com.example.exeption.MyException;
import com.example.facade.GetExtTaskListFacade;
import com.example.models.common.ExtTaskDto;
import com.example.models.common.ExtTaskListDto;
import com.example.services.task.ExtTaskService;
import com.example.services.task.TaskServiceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class ExtTaskServiceImp implements ExtTaskService, TaskServiceProvider {

    private final GetExtTaskListFacade getExtTaskListFacade;

    @Async
    @Override
    public List<ExtTaskDto> getTaskList() {
        System.out.println("Получение списка задач из TA: " + LocalDateTime.now());
        List<ExtTaskDto> result;
        result = getExtTaskListFacade.getExtTaskList(false);
        System.out.println("Результат получения задач TA: " + LocalDateTime.now() + " " + result.toString());
        return result;
    }

    @Async
    @Override
    public Future<ExtTaskListDto> getAllTaskList() {
        System.out.println("Получение списка задач из TA: " + LocalDateTime.now());
        ExtTaskListDto result = new ExtTaskListDto();
        result.setTasks(getExtTaskListFacade.getExtTaskList(true));
        System.out.println("Результат получения задач из TA: " + LocalDateTime.now() + " " + result.toString());
        return AsyncResult.forValue(result);
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
