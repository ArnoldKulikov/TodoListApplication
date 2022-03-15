package com.example.controllers;

import com.example.exeption.MyException;
import com.example.models.common.TaskDto;
import com.example.models.request.ChangeTaskRequestDto;
import com.example.models.request.CreateTaskRequestDto;
import com.example.models.response.TaskListResponseDto;
import com.example.models.response.TaskResponseDto;
import com.example.services.task.imp.CommonTaskServiceImp;
import com.example.services.task.imp.ExtTaskServiceImp;
import com.example.services.MapService;
import com.example.services.task.imp.TaskServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskServiceImp taskServiceImp;
    private final MapService mapService;
    private final ExtTaskServiceImp extTaskServiceImp;
    private final CommonTaskServiceImp commonTaskServiceImp;

    @PostMapping
    public TaskResponseDto createTask(@Valid @RequestBody CreateTaskRequestDto taskRequest) {
        TaskResponseDto response = new TaskResponseDto();
        response.setTask(mapService.convertToTaskDto(taskServiceImp.createTask(taskRequest.getDescription())));
        return response;
    }

    @GetMapping
    public TaskListResponseDto getTaskList() {
        TaskListResponseDto response = new TaskListResponseDto();
        ArrayList<TaskDto> list = new ArrayList<>();
        list.addAll(mapService.convertToListTaskDto(taskServiceImp.getOpenTaskList()));
        list.addAll(mapService.convertToListTaskDtoFromListExtTaskDto(extTaskServiceImp.getTaskList()));
        response.setTasks(list);
        return response;
    }

    @GetMapping("/all")
    public TaskListResponseDto getTaskListAll() {
        TaskListResponseDto response = new TaskListResponseDto();
        ArrayList<TaskDto> list = new ArrayList<>();
        list.addAll(mapService.convertToListTaskDto(taskServiceImp.getTaskList()));
        list.addAll(mapService.convertToListTaskDtoFromListExtTaskDto(extTaskServiceImp.getAllTaskList()));
        response.setTasks(list);
        return response;
    }

    @GetMapping("/search")
    public TaskListResponseDto getTaskListBySearch(
            @RequestParam("search") @NotNull String searchText
    ) {
        TaskListResponseDto response = new TaskListResponseDto();
        response.setTasks(mapService.convertToListTaskDto(taskServiceImp.searchTask(searchText)));
        return response;
    }

    @PatchMapping("/{task_id}")
    public TaskResponseDto changeTask(
            @PathVariable("task_id") @NonNull Long taskId,
            @Valid @RequestBody ChangeTaskRequestDto task) throws MyException {
        TaskResponseDto response = new TaskResponseDto();
        response.setTask(mapService.convertToTaskDto(taskServiceImp.changeTask(taskId, task.getClosed(), task.getDescription())));
        return response;
    }

    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable("task_id") @NonNull String taskId) throws MyException {
        commonTaskServiceImp.deleteTask(taskId);
    }
}
