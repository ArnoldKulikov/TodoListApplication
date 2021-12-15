package com.example.controllers;

import com.example.exeption.MyException;
import com.example.models.request.ChangeTaskRequestDto;
import com.example.models.request.CreateTaskRequestDto;
import com.example.models.response.TaskListResponseDto;
import com.example.models.response.TaskResponseDto;
import com.example.services.MapService;
import com.example.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final MapService mapService;

    @PostMapping
    public TaskResponseDto createTask(@Valid @RequestBody CreateTaskRequestDto taskRequest) {
        TaskResponseDto response = new TaskResponseDto();
        response.setTask(mapService.convertToTaskDto(taskService.createTask(taskRequest.getDescription())));
        return response;
    }

    @GetMapping
    public TaskListResponseDto getTaskList() {
        TaskListResponseDto response = new TaskListResponseDto();
        response.setTasks(mapService.convertToListTaskDto(taskService.getOpenTaskList()));
        return response;
    }

    @GetMapping("/all")
    public TaskListResponseDto getTaskListAll() {
        TaskListResponseDto response = new TaskListResponseDto();
        response.setTasks(mapService.convertToListTaskDto(taskService.getTaskList()));
        return response;
    }

    @GetMapping("/search")
    public TaskListResponseDto getTaskListBySearch(
            @RequestParam("search") @NotNull String searchText
    ) {
        TaskListResponseDto response = new TaskListResponseDto();
        response.setTasks(mapService.convertToListTaskDto(taskService.searchTask(searchText)));
        return response;
    }

    @PatchMapping("/{task_id}")
    public TaskResponseDto changeTask(
            @PathVariable("task_id") @NonNull Long taskId,
            @Valid @RequestBody ChangeTaskRequestDto task) throws MyException {
        TaskResponseDto response = new TaskResponseDto();
        response.setTask(mapService.convertToTaskDto(taskService.changeTask(taskId, task.getClosed(), task.getDescription())));
        return response;
    }

    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable("task_id") @NonNull Long taskId) throws MyException {
        taskService.deleteTask(taskId);
    }
}
