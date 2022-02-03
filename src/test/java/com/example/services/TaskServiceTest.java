package com.example.services;

import com.example.entities.Task;
import com.example.entities.User;
import com.example.exeption.MyException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private UserService userService;

    @Test
    void createTask() {

        User user = createUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        taskService.createTask("test4");

        Assert.assertTrue(user.getTasks().stream()
                .anyMatch(task -> task.getDescription() == "test4"));
        Assert.assertEquals(4, user.getTasks().size());
    }

    @Test
    void getOpenTaskList() {

        User user = createUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        List<Task> list = taskService.getOpenTaskList();

        Assert.assertEquals(1, list.size());

    }

    @Test
    void getTaskList() {

        User user = createUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        List<Task> list = taskService.getTaskList();

        Assert.assertEquals(3, list.size());

    }

    @Test
    void searchTask() {

        User user = createUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        List<Task> list = taskService.searchTask("test2");

        Assert.assertEquals(1, list.size());

    }

    @Test
    void changeTask() throws MyException {

        User user = createUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        Task task = taskService.changeTask(1l, true, "closed");

        assertEquals(1l, task.getId());
        assertEquals(true, task.getClosed());
        assertEquals("closed", task.getDescription());

    }

    @Test
    void changeTaskException() throws MyException {

        User user = createUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        Throwable thrown = assertThrows(MyException.class, () -> {
            taskService.changeTask(12l, true, "closed");
        });
        assertEquals("Задача не найдена", thrown.getMessage());

    }

    @Test
    void deleteTask() throws MyException {
        User user = createUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        taskService.deleteTask(1l);

        Assert.assertTrue(user.getTasks().stream()
                .filter(task -> task.getId() == 1l)
                .findFirst().isEmpty());
        Assert.assertEquals(2, user.getTasks().size());
    }

    @Test
    void deleteTaskException() throws MyException {

        User user = createUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        Throwable thrown = assertThrows(MyException.class, () -> {
            taskService.deleteTask(12l);
        });
        assertEquals("Задача не найдена", thrown.getMessage());

    }

    private User createUser() {
        User user = new User();
        List<Task> list = new ArrayList<>();
        list.add(new Task().setId(1l).setClosed(false).setDescription("test1"));
        list.add(new Task().setId(2l).setClosed(true).setDescription("test2"));
        list.add(new Task().setId(3l).setClosed(true).setDescription("test3"));
        user.setTasks(list);
        return user;
    }
}