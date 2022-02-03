package com.example.services;

import com.example.entities.Task;
import com.example.models.common.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class   MapServiceTest {

    @Test
    void convertToTaskDto() {

        MapService mapService = new MapService();
        Task task = new Task().setId(1l).setClosed(true).setDescription("test");

        TaskDto taskDto = mapService.convertToTaskDto(task);

        assertEquals(1l, taskDto.getTaskId());
        assertEquals(true, taskDto.getClosed());
        assertEquals("test", taskDto.getDescription());

    }

    @Test
    void convertToTask() {

        MapService mapService = new MapService();
        TaskDto taskDto = new TaskDto(1l, true, "test");

        Task task = mapService.convertToTask(taskDto);

        assertEquals(1l, task.getId());
        assertEquals(true, task.getClosed());
        assertEquals("test", task.getDescription());

    }

    @Test
    void convertToListTaskDto() {

        MapService mapService = new MapService();
        List<Task> list = new ArrayList<>();
        list.add(new Task().setId(1l).setClosed(false).setDescription("test1"));
        list.add(new Task().setId(2l).setClosed(false).setDescription("test2"));
        list.add(new Task().setId(3l).setClosed(false).setDescription("test3"));

        List<TaskDto> listDto = mapService.convertToListTaskDto(list);

        assertEquals(1l, listDto.get(0).getTaskId());
        assertEquals(false, listDto.get(1).getClosed());
        assertEquals("test3", listDto.get(2).getDescription());

    }

    @Test
    void convertToListTask() {

        MapService mapService = new MapService();
        List<TaskDto> listDto = new ArrayList<>();
        listDto.add(new TaskDto(1l, false, "test1"));
        listDto.add(new TaskDto(2l, true, "test2"));
        listDto.add(new TaskDto(3l, false, "test3"));

        List<Task> list = mapService.convertToListTask(listDto);

        assertEquals(1l, list.get(0).getId());
        assertEquals(true, list.get(1).getClosed());
        assertEquals("test3", list.get(2).getDescription());

    }
}