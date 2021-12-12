package com.example.models.response;

import com.example.entities.Task;
import com.example.models.common.TaskDto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TaskResponseDto {

    @NotNull
    private TaskDto task;

    public TaskResponseDto(Task newTask) {
        this.task = new TaskDto(newTask.getId(),newTask.getClosed(),newTask.getDescription());
    }
}