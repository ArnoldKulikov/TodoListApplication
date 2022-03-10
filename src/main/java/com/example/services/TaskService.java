package com.example.services;

import com.example.entities.Task;
import com.example.entities.User;
import com.example.exeption.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final UserService userService;
    private final ExtTaskService extTaskService;

    public Task createTask(String description) {
        User user = userService.getCurrentUser();
        Task localTask = taskServices(description);
        user.getTasks().add(localTask);
        return localTask;
    }

    public List<Task> getOpenTaskList() {
        User user = userService.getCurrentUser();
        return user.getTasks()
                .stream()
                .filter(task -> task.getClosed() == false)
                .sorted(Comparator.comparing(Task::getId))
                .collect(Collectors.toList());
    }

    public List<Task> getTaskList() {
        User user = userService.getCurrentUser();
        return user.getTasks()
                .stream()
                .sorted(Comparator.comparing(Task::getId))
                .collect(Collectors.toList());
    }

    public List<Task> searchTask(String search) {
        User user = userService.getCurrentUser();
        return user.getTasks()
                .stream()
                .filter(task -> task.getDescription().contains(search))
                .sorted(Comparator.comparing(Task::getId))
                .collect(Collectors.toList());
    }

    public Task changeTask(Long taskId, Boolean closed, String description) throws MyException {
        User user = userService.getCurrentUser();
        Task localTask = user.getTasks()
                .stream()
                .filter(task -> task.getId() == taskId)
                .findFirst().orElseThrow(() -> new MyException("taskNotFound"));
        if (closed != null) localTask.setClosed(closed);
        if (description != null) localTask.setDescription(description);
        return localTask;
    }

    public void deleteTask(String taskId) throws MyException {
        if (taskId.contains("EXT_")){
            extTaskService.deleteExtTask(taskId.substring(4));
        } else {
            User user = userService.getCurrentUser();
            Task localTask = user.getTasks()
                    .stream()
                    .filter(task -> task.getId() == Long.parseLong(taskId.substring(5)))
                    .findFirst().orElseThrow(() -> new MyException("taskNotFound"));
            user.getTasks().remove(localTask);
        }
    }

    private Task taskServices(String description) {
        return new Task()
                .setClosed(false)
                .setDescription(description);
    }
}
