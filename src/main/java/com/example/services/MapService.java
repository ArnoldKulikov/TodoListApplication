package com.example.services;

import com.example.entities.Task;
import com.example.models.common.ExtTaskDto;
import com.example.models.common.TaskDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapService {

    private ModelMapper modelMapper = new ModelMapper();

    public TaskDto convertToTaskDto(Task task) {
        TaskDto taskDto = modelMapper.map(task, TaskDto.class);
        taskDto.setTaskId("TDLA_" + taskDto.getTaskId());
        return taskDto;
    }

    public Task convertToTask(TaskDto taskDto) {
        return modelMapper.map(taskDto, Task.class);
    }

    public List<TaskDto> convertToListTaskDto(List<Task> task) {
        return task.stream()
                .map(this::convertToTaskDto)
                .collect(Collectors.toList());
    }

    public List<Task> convertToListTask(List<TaskDto> taskDto) {
        return taskDto.stream()
                .map(this::convertToTask)
                .collect(Collectors.toList());
    }

    public TaskDto convertToTaskDto(ExtTaskDto extTaskDto) {
        TaskDto taskDto = modelMapper.map(extTaskDto, TaskDto.class);
        taskDto.setTaskId("EXT_" + taskDto.getTaskId());
        taskDto.setClosed(extTaskDto.getDone());
        return taskDto;
    }

    public List<TaskDto> convertToListTaskDtoFromListExtTaskDto(List<ExtTaskDto> extTaskDto) {
        return extTaskDto.stream()
                .map(this::convertToTaskDto)
                .collect(Collectors.toList());
    }

}
