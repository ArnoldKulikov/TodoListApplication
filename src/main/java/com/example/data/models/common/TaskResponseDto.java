package com.example.data.models.common;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
public class TaskResponseDto {

    @NotNull
    private TaskDto task;

    public TaskResponseDto(TaskDto task) {
        this.task = task;
    }
}