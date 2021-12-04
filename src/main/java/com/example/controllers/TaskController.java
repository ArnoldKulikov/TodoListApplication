package com.example.controllers;

import com.example.data.TaskRepository;
import com.example.data.models.common.TaskDto;
import com.example.data.models.common.TaskListResponseDto;
import com.example.data.models.common.TaskResponseDto;
import com.example.data.models.request.ChangeTaskRequestDto;
import com.example.data.models.request.CreateTaskRequestDto;
import com.example.exeption.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping
    public TaskResponseDto createTask(@Valid @RequestBody CreateTaskRequestDto taskRequest) {
        TaskDto localTask = new TaskDto();
        localTask = localTask.taskServices(taskRequest.getDescription());

        taskRepository.save(localTask);
        if (log.isDebugEnabled()) {
            log.debug(taskRepository.findById(localTask.getId()).toString());
        }
        return new TaskResponseDto(localTask);
    }

    @GetMapping
    public TaskListResponseDto getTaskList() {
        if (log.isDebugEnabled()) {
            log.debug(taskRepository.findAll().toString());
        }
        return new TaskListResponseDto(taskRepository.findByClosed(false, Sort.by("Id")));
    }

    @GetMapping("/all")
    public TaskListResponseDto getTaskListAll() {
        if (log.isDebugEnabled()) {
            log.debug(taskRepository.findAll().toString());
        }
        return new TaskListResponseDto(taskRepository.findAll(Sort.by("Id")));
    }

    @GetMapping("/search")
    public TaskListResponseDto getTaskListBySearch(
            @RequestParam("search") @NotNull String searchText
    ) {
        if (log.isDebugEnabled()) {
            log.debug(taskRepository.findAll().toString());
        }
        return new TaskListResponseDto(taskRepository.findByDescription(searchText, Sort.by("Id")));
    }

    @PatchMapping("/{task_id}")
    public TaskResponseDto changeTask(
            @PathVariable("task_id") @NonNull Long taskId,
            @Valid @RequestBody ChangeTaskRequestDto task) throws MyException {
        Optional<TaskDto> result = taskRepository.findById(taskId);
        if (result.isPresent()) {
            TaskDto localTask = result.get();
            if (task.getClosed() != null) localTask.setClosed(task.getClosed());
            if (task.getDescription() != null) localTask.setDescription(task.getDescription());
            taskRepository.save(localTask);
            if (log.isDebugEnabled()) {
                log.debug(taskRepository.findAll().toString());
            }
            return new TaskResponseDto(localTask);
        }
        throw new MyException("taskNotFound");
    }

    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable("task_id") @NonNull Long taskId) throws EmptyResultDataAccessException {
        taskRepository.deleteById(taskId);
        if (log.isDebugEnabled()) {
            log.debug(taskRepository.findAll().toString());
        }
    }
}
