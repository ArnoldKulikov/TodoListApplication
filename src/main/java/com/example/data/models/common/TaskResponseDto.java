package com.example.data.models.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TaskResponseDto {

    private TaskDto task;

    public TaskResponseDto(TaskDto task) {
        this.task = task;
    }
}