package com.example.services;

import com.example.entities.Task;
import com.example.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final UserService userService;

    public Task createTask(String description) {
        User user = userService.getCurrentUser();
        Task localTask = taskServices(description);
        user.getTasks().add(localTask);
        return localTask;
    }

    private Task taskServices(String description) {
        return new Task()
                .setClosed(false)
                .setDescription(description);
    }
}
