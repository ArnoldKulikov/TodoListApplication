package com.example.services;

import com.example.entities.Task;
import com.example.models.common.TaskDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapService {

    private ModelMapper modelMapper = new ModelMapper();

    public TaskDto convertToTaskDto(Task task) {
        return modelMapper.map(task, TaskDto.class);
    }

    public Task convertToTask(TaskDto taskDto) {
        return modelMapper.map(taskDto, Task.class);
    }

    public List<TaskDto> convertToListTaskDto(List<Task> task) {
        List<TaskDto> tasks = new ArrayList<>();
        for (Task localTask: task)
        {
            tasks.add(modelMapper.map(task, TaskDto.class));
        }
        return tasks;
    }

    public List<Task> convertToListTask(List<TaskDto> taskDto) {
        List<Task> tasks = new ArrayList<>();
        for (TaskDto localTask: taskDto)
        {
            tasks.add(modelMapper.map(taskDto, Task.class));
        }
        return tasks;
    }

}
