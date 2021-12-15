package com.example.models.response;

import com.example.models.common.TaskDto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TaskResponseDto {

    @NotNull
    private TaskDto task;

}