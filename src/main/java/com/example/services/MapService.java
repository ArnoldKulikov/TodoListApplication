package com.example.services;

import com.example.entities.Task;
import com.example.models.common.TaskDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
        return task.stream()
                .map(element -> modelMapper.map(element, TaskDto.class))
                .collect(Collectors.toList());
    }

    public List<Task> convertToListTask(List<TaskDto> taskDto) {
        return taskDto.stream()
                .map(element -> modelMapper.map(element, Task.class))
                .collect(Collectors.toList());
    }

}
