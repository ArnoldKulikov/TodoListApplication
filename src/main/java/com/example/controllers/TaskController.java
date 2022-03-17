package com.example.controllers;

import com.example.entities.User;
import com.example.exeption.MyException;
import com.example.models.common.TaskDto;
import com.example.models.common.TaskListDto;
import com.example.models.request.ChangeTaskRequestDto;
import com.example.models.request.CreateTaskRequestDto;
import com.example.models.response.TaskListResponseDto;
import com.example.models.response.TaskResponseDto;
import com.example.services.UserService;
import com.example.services.task.CommonTaskService;
import com.example.services.task.ExtTaskService;
import com.example.services.task.TaskService;
import com.example.services.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

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
    public TaskListResponseDto getTaskList() {
        TaskListResponseDto response = new TaskListResponseDto();
        ArrayList<TaskDto> list = new ArrayList<>();
//        list.addAll(mapService.convertToListTaskDto(taskService.getOpenTaskList()));
//        list.addAll(mapService.convertToListTaskDtoFromListExtTaskDto(extTaskService.getTaskList()));
        response.setTasks(list);
        return response;
    }

    @GetMapping("/all")
    public TaskListResponseDto getTaskListAll() throws InterruptedException {
        User user = userService.getCurrentUser();
        TaskListResponseDto response = new TaskListResponseDto();
        LinkedBlockingQueue<TaskListDto> list = new LinkedBlockingQueue<>();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable getTaskList = () -> {
            try {
                list.put(mapService.convertToListTaskDto(taskService.getTaskList(user).get().getTasks()));
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            };
        Runnable getExtTaskList = () -> {
            try {
                list.put(mapService.convertToListTaskDtoFromListExtTaskDto(extTaskService.getAllTaskList().get().getTasks()));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        };

        executor.execute(getTaskList);
        executor.execute(getExtTaskList);
        executor.shutdown();

        while (!executor.isTerminated()) {
            Thread.sleep(60l);
        }

        List<TaskDto> result = new ArrayList<>();
        while (!(list.size() == 0)) {
            result.add(list.take());
        }
        response.setTasks(result);
        return response;
    }

    @GetMapping("/search")
    public TaskListResponseDto getTaskListBySearch(
            @RequestParam("search") @NotNull String searchText
    ) {
        TaskListResponseDto response = new TaskListResponseDto();
//        response.setTasks(mapService.convertToListTaskDto(taskService.searchTask(searchText)));
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
