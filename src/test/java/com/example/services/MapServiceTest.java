package com.example.services;

import com.example.entities.Task;
import com.example.models.common.TaskDto;
import com.example.models.common.TaskListDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapServiceTest {

    MapService mapService = new MapService();

    @Test
    void convertToTaskDto() {

        Task task = new Task().setId(1l).setClosed(true).setDescription("test");

        TaskDto taskDto = mapService.convertToTaskDto(task);

        assertEquals(1l, taskDto.getTaskId());
        assertEquals(true, taskDto.getClosed());
        assertEquals("test", taskDto.getDescription());

    }

    @Test
    void convertToTask() {

        TaskDto taskDto = new TaskDto("1", true, "test");

        Task task = mapService.convertToTask(taskDto);

        assertEquals(1l, task.getId());
        assertEquals(true, task.getClosed());
        assertEquals("test", task.getDescription());

    }

    @Test
    void convertToListTaskDto() {

        List<Task> list = new ArrayList<>();
        list.add(new Task().setId(1l).setClosed(false).setDescription("test1"));
        list.add(new Task().setId(2l).setClosed(false).setDescription("test2"));
        list.add(new Task().setId(3l).setClosed(false).setDescription("test3"));

        TaskListDto listDto = new TaskListDto();
        listDto.setTasks(mapService.convertToListTaskDto(list).getTasks());
        TaskDto localTask = listDto.getTasks().get(0);

        assertEquals(1l, localTask.getTaskId());
        assertEquals(false, localTask.getClosed());
        assertEquals("test1", localTask.getDescription());

    }

    @Test
    void convertToListTask() {

        List<TaskDto> listDto = new ArrayList<>();
        listDto.add(new TaskDto("1", false, "test1"));
        listDto.add(new TaskDto("2", true, "test2"));
        listDto.add(new TaskDto("3", false, "test3"));

        List<Task> list = mapService.convertToListTask(listDto);
        Task localTask = list.get(0);

        assertEquals(1l, localTask.getId());
        assertEquals(false, localTask.getClosed());
        assertEquals("test1", localTask.getDescription());

    }
}