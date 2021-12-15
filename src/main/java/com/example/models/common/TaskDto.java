package com.example.models.common;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class TaskDto {

    @NotBlank
    @NotNull
    private Long taskId;

    @NotBlank
    @NotNull
    private Boolean closed;

    @NotBlank
    @NotNull
    private String description;

    public TaskDto(@NotBlank @NotNull Long taskId, @NotBlank @NotNull Boolean closed, @NotBlank @NotNull String description) {
        this.taskId = taskId;
        this.closed = closed;
        this.description = description;
    }
}