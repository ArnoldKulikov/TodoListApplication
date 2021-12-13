package com.example.controllers;

import com.example.exeption.MyException;
import com.example.models.request.ChangeTaskRequestDto;
import com.example.models.request.CreateTaskRequestDto;
import com.example.models.response.TaskListResponseDto;
import com.example.models.response.TaskResponseDto;
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

    @PostMapping
    public TaskResponseDto createTask(@Valid @RequestBody CreateTaskRequestDto taskRequest) {
        return new TaskResponseDto(taskService.createTask(taskRequest.getDescription()));
    }

    @GetMapping
    public TaskListResponseDto getTaskList() {
        return new TaskListResponseDto(taskService.getOpenTaskList());
    }

    @GetMapping("/all")
    public TaskListResponseDto getTaskListAll() {
        return new TaskListResponseDto(taskService.getTaskList());
    }

    @GetMapping("/search")
    public TaskListResponseDto getTaskListBySearch(
            @RequestParam("search") @NotNull String searchText
    ) {
        return new TaskListResponseDto(taskService.searchTask(searchText));
    }

    @PatchMapping("/{task_id}")
    public TaskResponseDto changeTask(
            @PathVariable("task_id") @NonNull Long taskId,
            @Valid @RequestBody ChangeTaskRequestDto task) throws MyException {
        return new TaskResponseDto(taskService.changeTask(taskId, task.getClosed(), task.getDescription()));
    }

    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable("task_id") @NonNull Long taskId) throws MyException {
        taskService.deleteTask(taskId);
    }
}
