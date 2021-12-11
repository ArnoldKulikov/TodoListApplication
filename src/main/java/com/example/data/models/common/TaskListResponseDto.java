package com.example.data.models.common;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TaskListResponseDto {

    @NotNull
    private List<TaskDto> tasks;

    public TaskListResponseDto(@NotNull List<Task> taskList) {
        for (Task task: taskList) {
            TaskDto localTask = new TaskDto();
            localTask.setTaskId(task.getId());
            localTask.setClosed(task.getClosed());
            localTask.setDescription(task.getDescription());
            this.tasks.add(localTask);
        }
    }
}