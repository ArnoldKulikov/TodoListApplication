package com.example.services.task.imp;

import com.example.exeption.MyException;
import com.example.facade.GetExtTaskListFacade;
import com.example.models.common.TaskDto;
import com.example.services.MapService;
import com.example.services.task.ExtTaskService;
import com.example.services.task.TaskServiceProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Future;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExtTaskServiceImp implements ExtTaskService, TaskServiceProvider {

    private final GetExtTaskListFacade getExtTaskListFacade;
    private final MapService mapService;

    @Async
    @Override
    public Future<List<TaskDto>> getTaskList() {
        log.debug("Получение списка задач из TA: " + LocalDateTime.now());
        List<TaskDto> result;
        result = mapService.convertToListTaskDtoFromListExtTaskDto(getExtTaskListFacade.getExtTaskList(false));
        log.debug("Результат получения задач TA: " + LocalDateTime.now() + " " + result.toString());
        return AsyncResult.forValue(result);
    }

    @Async
    @Override
    public Future<List<TaskDto>> getAllTaskList() {
        log.debug("Получение списка задач из TA: " + LocalDateTime.now());
        List<TaskDto> result = mapService.convertToListTaskDtoFromListExtTaskDto(getExtTaskListFacade.getExtTaskList(true));
        log.debug("Результат получения задач из TA: " + LocalDateTime.now() + " " + result.toString());
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
