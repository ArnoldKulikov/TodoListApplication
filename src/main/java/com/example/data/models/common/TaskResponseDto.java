package com.example.data.models.common;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
public class TaskResponseDto {

    @NotNull
    private TaskDto task;

    public TaskResponseDto(Task newTask) {
        this.task = new TaskDto(newTask.getId(),newTask.getClosed(),newTask.getDescription());
    }
}