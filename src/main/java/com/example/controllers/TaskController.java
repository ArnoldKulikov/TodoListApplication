package com.example.controllers;

import com.example.entities.User;
import com.example.exeption.MyException;
import com.example.models.common.TaskDto;
import com.example.models.request.ChangeTaskRequestDto;
import com.example.models.request.CreateTaskRequestDto;
import com.example.models.response.TaskListResponseDto;
import com.example.models.response.TaskResponseDto;
import com.example.services.MapService;
import com.example.services.UserService;
import com.example.services.task.CommonTaskService;
import com.example.services.task.ExtTaskService;
import com.example.services.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final MapService mapService;
    private final ExtTaskService extTaskService;
    private final CommonTaskService commonTaskService;
    private final UserService userService;

    @PostMapping
    public TaskResponseDto createTask(@Valid @RequestBody CreateTaskRequestDto taskRequest) {
        TaskResponseDto response = new TaskResponseDto();
        response.setTask(mapService.convertToTaskDto(taskService.createTask(taskRequest.getDescription())));
        return response;
    }

    @GetMapping
    public TaskListResponseDto getTaskList() throws InterruptedException, ExecutionException {

        User user = userService.getCurrentUser();
        TaskListResponseDto response = new TaskListResponseDto();

        Future<List<TaskDto>> extTask
                = CompletableFuture.supplyAsync(extTaskService::getTaskList).get();
        Future<List<TaskDto>> getTask
                = CompletableFuture.supplyAsync(() -> taskService.getOpenTaskList(user)).get();

        while (!extTask.isDone() && !getTask.isDone()) {
            Thread.sleep(50L);
        }

        List<TaskDto> extTaskList = extTask.get();
        List<TaskDto> getTaskList = getTask.get();

        List<TaskDto> result = Stream.of(extTaskList, getTaskList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        response.setTasks(result);

        return response;
    }

    @GetMapping("/all")
    public TaskListResponseDto getTaskListAll() throws InterruptedException, ExecutionException {

        User user = userService.getCurrentUser();
        TaskListResponseDto response = new TaskListResponseDto();

        Future<List<TaskDto>> extTask
                = CompletableFuture.supplyAsync(extTaskService::getAllTaskList).get();
        Future<List<TaskDto>> getTask
                = CompletableFuture.supplyAsync(() -> taskService.getTaskList(user)).get();

        while (!extTask.isDone() && !getTask.isDone()) {
            Thread.sleep(50L);
        }

        List<TaskDto> extTaskList = extTask.get();
        List<TaskDto> getTaskList = getTask.get();

        List<TaskDto> result = Stream.of(extTaskList, getTaskList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        response.setTasks(result);

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
    public void deleteTask(@PathVariable("task_id") @NonNull String taskId) throws MyException {
        commonTaskService.deleteTask(taskId);
    }
}
