package com.example;

import com.example.data.TaskListRepository;
import com.example.data.models.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskListRepository taskListRepository;

    @GetMapping("/task")
    public List<Task> getAllUsingGET() {
        return taskListRepository.getAllTasks();
    }

}
