package com.example;

import com.example.data.TaskListRepository;
import com.example.data.models.TaskDto;
import com.example.exeption.MyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskListRepository taskListRepository;

    @PostMapping("/taskList")
    public TaskDto createTask(@Valid @RequestBody TaskDto task) {
        TaskDto localTask = new TaskDto()
                .setClosed(false)
                .setDescription(task.getDescription());

        taskListRepository.createTask(localTask);
        log.debug(taskListRepository.getAllTasks().toString());
        return localTask;
    }

    @GetMapping("/taskList")
    public List<TaskDto> getTaskList() {
        log.debug(taskListRepository.getAllTasks().toString());
        return taskListRepository.getTaskByStatus(false);
    }

    @GetMapping("/taskList/all")
    public List<TaskDto> getAllTaskList() {
        log.debug(taskListRepository.getAllTasks().toString());
        return taskListRepository.getAllTasks();
    }

    @GetMapping("/taskList/search")
    public List<TaskDto> getTaskListByDescription(
            @RequestParam(value = "search", required = true) @NotNull String searchText
    ) {
        log.debug(taskListRepository.getAllTasks().toString());
        return taskListRepository.getTaskByDescription(searchText);
    }

    @DeleteMapping("/taskList/{task_id}")
    public void deleteTask(@PathVariable("task_id") @NonNull Long taskId) throws MyException {
        taskListRepository.deleteTaskById(taskId);
        log.debug(taskListRepository.getAllTasks().toString());
    }
}
