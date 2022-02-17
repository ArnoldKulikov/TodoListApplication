package com.example.main;

import com.example.controllers.AdminController;
import com.example.controllers.TaskController;
import com.example.services.MapService;
import com.example.services.TaskService;
import com.example.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestingComponentType {

    @Autowired
    private AdminController adminController;

    @Autowired
    private TaskController taskController;

    @Autowired
    private MapService mapService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Test
    public void loadsAdminController() {
        assertTrue(adminController instanceof AdminController);
    }

    @Test
    public void loadsTaskController() {
        assertTrue(taskController instanceof TaskController);
    }

    @Test
    public void loadsMapService() {
        assertTrue(mapService instanceof MapService);
    }

    @Test
    public void loadsTaskService() {
        assertTrue(taskService instanceof TaskService);
    }

    @Test
    public void loadsUserService() {
        assertTrue(userService instanceof UserService);
    }
}