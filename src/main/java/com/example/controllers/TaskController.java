package com.example.controllers;

import com.example.data.TaskRepository;
import com.example.data.models.common.TaskDto;
import com.example.data.models.common.TaskListResponseDto;
import com.example.data.models.common.TaskResponseDto;
import com.example.data.models.request.ChangeTaskRequestDto;
import com.example.data.models.request.CreateTaskRequestDto;
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
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;

    @PostMapping
    public TaskResponseDto createTask(@Valid @RequestBody CreateTaskRequestDto taskRequest) {
        TaskDto localTask = new TaskDto();
        localTask = localTask.taskServices(taskRequest.getDescription());

        taskRepository.createTask(localTask);
        if (log.isDebugEnabled()) {
            log.debug(taskRepository.getAllTasks().toString());
        }
        return new TaskResponseDto(localTask);
    }

    @GetMapping
    public TaskListResponseDto getTaskList() {
        log.debug(taskRepository.getAllTasks().toString());
        return new TaskListResponseDto(taskRepository.getTaskByStatus(false));
    }

    @GetMapping("/all")
    public TaskListResponseDto getTaskListAll() {
        log.debug(taskRepository.getAllTasks().toString());
        return new TaskListResponseDto(taskRepository.getAllTasks());
    }

    @GetMapping("/search")
    public TaskListResponseDto getTaskListBySearch(
            @RequestParam("search") @NotNull String searchText
    ) {
        log.debug(taskRepository.getAllTasks().toString());
        return new TaskListResponseDto(taskRepository.search(searchText));
    }

    @PatchMapping("/{task_id}")
    public TaskResponseDto changeTask(
            @PathVariable("task_id") @NonNull Long taskId,
            @Valid @RequestBody ChangeTaskRequestDto task) throws MyException {
        TaskDto localTask = taskRepository.getTaskById(taskId);
        if (task.isClosed() || !task.isClosed()) localTask.setClosed(task.isClosed());
        if (task.getDescription() != null) localTask.setDescription(task.getDescription());
        taskRepository.updateTask(localTask);
        log.debug(taskRepository.getAllTasks().toString());
        return new TaskResponseDto(localTask);
    }

    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable("task_id") @NonNull Long taskId) throws MyException {
        taskRepository.deleteTaskById(taskId);
        log.debug(taskRepository.getAllTasks().toString());
    }
}
