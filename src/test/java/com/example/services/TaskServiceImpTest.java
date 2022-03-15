package com.example.services;

import com.example.entities.Task;
import com.example.entities.User;
import com.example.exeption.MyException;
import com.example.services.task.imp.ExtTaskServiceImp;
import com.example.services.task.imp.TaskServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceImpTest {

    UserService userService = Mockito.mock(UserService.class);
    ExtTaskServiceImp extTaskServiceImp = Mockito.mock(ExtTaskServiceImp.class);
    TaskServiceImp taskServiceImp = new TaskServiceImp(userService, extTaskServiceImp);

    @Test
    void createTask() {

        User user = createUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        taskServiceImp.createTask("test4");

        assertTrue(user.getTasks().stream()
                .anyMatch(t -> t.getDescription().equals("test4")));
        assertEquals(4, user.getTasks().size());
        Mockito.verify(userService, Mockito.times(1)).getCurrentUser();
    }

    @Test
    void getOpenTaskList() {

        User user = createUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        List<Task> list = taskServiceImp.getOpenTaskList();

        assertEquals(1, list.size());
        Mockito.verify(userService, Mockito.times(1)).getCurrentUser();

    }

    @Test
    void getTaskList() {

        User user = createUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        List<Task> list = taskServiceImp.getTaskList();

        assertEquals(3, list.size());
        Mockito.verify(userService, Mockito.times(1)).getCurrentUser();
    }

    @Test
    void searchTask() {

        User user = createUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        List<Task> list = taskServiceImp.searchTask("test2");

        assertEquals(1, list.size());
        Mockito.verify(userService, Mockito.times(1)).getCurrentUser();

    }

    @Test
    void changeTask() throws MyException {

        User user = createUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        Task task = taskServiceImp.changeTask(1l, true, "closed");

        assertEquals(1l, task.getId());
        assertEquals(true, task.getClosed());
        assertEquals("closed", task.getDescription());
        Mockito.verify(userService, Mockito.times(1)).getCurrentUser();
    }

    @Test
    void changeTaskException() throws MyException {

        User user = createUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        Throwable thrown = assertThrows(MyException.class, () -> {
            taskServiceImp.changeTask(12l, true, "closed");
        });
        assertEquals("Задача не найдена", thrown.getMessage());
        Mockito.verify(userService, Mockito.times(1)).getCurrentUser();
    }

    @Test
    void deleteTask() throws MyException {
        User user = createUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        taskServiceImp.deleteTask("TDLA_1");

        assertTrue(user.getTasks().stream()
                .filter(task -> task.getId() == 1l)
                .findFirst().isEmpty());
        assertEquals(2, user.getTasks().size());
        Mockito.verify(userService, Mockito.times(1)).getCurrentUser();
    }

    @Test
    void deleteTaskException() throws MyException {

        User user = createUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        Throwable thrown = assertThrows(MyException.class, () -> {
            taskServiceImp.deleteTask("TDLA_12");
        });
        assertEquals("Задача не найдена", thrown.getMessage());
        Mockito.verify(userService, Mockito.times(1)).getCurrentUser();

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