package com.example.data.models.common;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TaskListResponseDto {

    @NotNull
    private List<TaskDto> taskList;

    public TaskListResponseDto(@NotNull List<TaskDto> taskList) {
        this.taskList = taskList;
    }
}