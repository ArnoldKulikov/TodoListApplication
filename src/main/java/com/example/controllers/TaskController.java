package com.example.controllers;

import com.example.repositories.TaskRepository;
import com.example.repositories.UserRepository;
import com.example.entities.Task;
import com.example.models.response.TaskListResponseDto;
import com.example.models.response.TaskResponseDto;
import com.example.models.request.ChangeTaskRequestDto;
import com.example.models.request.CreateTaskRequestDto;
import com.example.exeption.MyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final Long userId = 1L;

    @PostMapping
    public TaskResponseDto createTask(@Valid @RequestBody CreateTaskRequestDto taskRequest) {
        Task localTask = new Task();
        localTask = localTask.taskServices(taskRequest.getDescription(), userRepository.getById(userId));
        return new TaskResponseDto(taskRepository.save(localTask));
    }

    @GetMapping
    public TaskListResponseDto getTaskList() {
        return new TaskListResponseDto(taskRepository.findByClosedOrderByIdAsc(false));
    }

    @GetMapping("/all")
    public TaskListResponseDto getTaskListAll() {
        List<Task> localTasks = taskRepository.findAllByOrderByIdAsc();
        return new TaskListResponseDto(localTasks);
    }

    @GetMapping("/search")
    public TaskListResponseDto getTaskListBySearch(
            @RequestParam("search") @NotNull String searchText
    ) {
        return new TaskListResponseDto(taskRepository.findByDescriptionContainingOrderByIdAsc(searchText));
    }

    @PatchMapping("/{task_id}")
    public TaskResponseDto changeTask(
            @PathVariable("task_id") @NonNull Long taskId,
            @Valid @RequestBody ChangeTaskRequestDto task) throws MyException {
        Optional<Task> result = taskRepository.findById(taskId);
        if (result.isPresent()) {
            Task localTask = result.get();
            if (task.getClosed() != null) localTask.setClosed(task.getClosed());
            if (task.getDescription() != null) localTask.setDescription(task.getDescription());
            taskRepository.save(localTask);
            return new TaskResponseDto(localTask);
        }
        throw new MyException("taskNotFound");
    }

    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable("task_id") @NonNull Long taskId) throws EmptyResultDataAccessException {
        taskRepository.deleteById(taskId);
    }
}
