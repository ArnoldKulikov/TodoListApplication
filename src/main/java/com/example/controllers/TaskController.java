package com.example.controllers;

import com.example.data.TaskListRepository;
import com.example.data.models.common.TaskDto;
import com.example.data.models.request.ChangeTaskRequestDto;
import com.example.data.models.request.CreateTaskRequestDto;
import com.example.data.models.response.*;
import com.example.exeption.MyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskListRepository taskListRepository;

    @PostMapping("/taskList")
    public CreateTaskResponseDto createTask(@Valid @RequestBody CreateTaskRequestDto task) {
        TaskDto localTask = new TaskDto()
                .setClosed(false)
                .setDescription(task.getDescription());

        taskListRepository.createTask(localTask);
        log.debug(taskListRepository.getAllTasks().toString());
        return new CreateTaskResponseDto(localTask);
    }

    @GetMapping("/taskList")
    public GetTaskListResponseDto getTaskList() {
        log.debug(taskListRepository.getAllTasks().toString());
        return new GetTaskListResponseDto(taskListRepository.getTaskByStatus(false));
    }

    @GetMapping("/taskList/all")
    public GetTaskListAllResponseDto getTaskListAll() {
        log.debug(taskListRepository.getAllTasks().toString());
        return new GetTaskListAllResponseDto(taskListRepository.getAllTasks());
    }

    @GetMapping("/taskList/search")
    public GetTaskListBySearchResponseDto getTaskListBySearch(
            @RequestParam(value = "search") @NotNull String searchText
    ) {
        log.debug(taskListRepository.getAllTasks().toString());
        return new GetTaskListBySearchResponseDto(taskListRepository.getTaskByDescription(searchText));
    }

    @PatchMapping("/taskList/{task_id}")
    public ChangeTaskResponseDto changeTask(
            @PathVariable("task_id") @NonNull Long taskId,
            @Valid @RequestBody ChangeTaskRequestDto task) throws MyException {
        TaskDto localTask = taskListRepository.getTaskById(taskId);
        localTask.setClosed(task.isClosed());
        localTask.setDescription(task.getDescription());
        taskListRepository.updateTask(localTask);
        log.debug(taskListRepository.getAllTasks().toString());
        return new ChangeTaskResponseDto(localTask);
    }

    @DeleteMapping("/taskList/{task_id}")
    public void deleteTask(@PathVariable("task_id") @NonNull Long taskId) throws MyException {
        taskListRepository.deleteTaskById(taskId);
        log.debug(taskListRepository.getAllTasks().toString());
    }
}
