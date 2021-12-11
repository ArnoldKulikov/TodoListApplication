package com.example.data.models.common;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
public class TaskResponseDto {

    @NotNull
    private TaskDto task;

    public TaskResponseDto(Task task) {
        this.task.setTaskId(task.getId());
        this.task.setClosed(task.getClosed());
        this.task.setDescription(task.getDescription());
    }
}