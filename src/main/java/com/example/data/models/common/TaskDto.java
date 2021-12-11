package com.example.data.models.common;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
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

}