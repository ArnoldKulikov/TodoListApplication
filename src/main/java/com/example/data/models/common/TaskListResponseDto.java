package com.example.data.models.common;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class TaskListResponseDto {

    @NotNull
    private List<TaskDto> tasks = new ArrayList<>();

    public TaskListResponseDto(@NotNull List<Task> taskList) {
        for (Task task: taskList) {
            TaskDto localTask = new TaskDto(task.getId(),task.getClosed(),task.getDescription());
            this.tasks.add(localTask);
        }
    }
}