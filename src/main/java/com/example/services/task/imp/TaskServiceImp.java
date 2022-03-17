package com.example.services.task.imp;

import com.example.entities.Task;
import com.example.entities.User;
import com.example.exeption.MyException;
import com.example.models.common.TaskList;
import com.example.services.UserService;
import com.example.services.task.TaskService;
import com.example.services.task.TaskServiceProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImp implements TaskService, TaskServiceProvider {

    private final UserService userService;

    @Override
    public Task createTask(String description) {
        User user = userService.getCurrentUser();
        Task localTask = taskServices(description);
        user.getTasks().add(localTask);
        return localTask;
    }

    @Async
    @Override
    public List<Task> getOpenTaskList() {

        User user = userService.getCurrentUser();
        List<Task> result;
        System.out.println("Получение списка задач из БД");
        result = user.getTasks()
                .stream()
                .filter(task -> task.getClosed() == false)
                .sorted(Comparator.comparing(Task::getId))
                .collect(Collectors.toList());
        System.out.println("Результат получения задач: " + result.toString());
        return result;
    }

    @Async
    @Override
    public Future<TaskList> getTaskList(User user) {

        TaskList result = new TaskList();

        System.out.println("Получение списка задач из БД: " + LocalDateTime.now());

        result.setTasks(user.getTasks()
                .stream()
                .sorted(Comparator.comparing(Task::getId))
                .collect(Collectors.toList()));
        System.out.println("Результат получения задач из БД: " + LocalDateTime.now() + " " + result.toString());

        return AsyncResult.forValue(result);
    }

    @Override
    public List<Task> searchTask(String search) {
        User user = userService.getCurrentUser();
        return user.getTasks()
                .stream()
                .filter(task -> task.getDescription().contains(search))
                .sorted(Comparator.comparing(Task::getId))
                .collect(Collectors.toList());
    }

    @Override
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

    private Task taskServices(String description) {
        return new Task()
                .setClosed(false)
                .setDescription(description);
    }

    @Override
    public Boolean checkTaskId(String taskId) {
        return taskId.contains("TDLA_");
    }

    @Override
    public void deleteTask(String taskId) throws MyException {
        User user = userService.getCurrentUser();
        Task localTask = user.getTasks()
                .stream()
                .filter(task -> task.getId() == Long.parseLong(taskId.substring(5)))
                .findFirst().orElseThrow(() -> new MyException("taskNotFound"));
        user.getTasks().remove(localTask);
    }
}
