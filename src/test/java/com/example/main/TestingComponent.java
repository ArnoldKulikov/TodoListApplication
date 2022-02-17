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
class TestingComponent {

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
        assertNotNull(adminController);
    }

    @Test
    public void loadsTaskController() {
        assertNotNull(taskController);
    }

    @Test
    public void loadsMapService() {
        assertNotNull(mapService);
    }

    @Test
    public void loadsTaskService() {
        assertNotNull(taskService);
    }

    @Test
    public void loadsUserService() {
        assertNotNull(userService);
    }
}