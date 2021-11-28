package com.example.data.models.response;

import com.example.data.models.common.TaskDto;
import com.example.data.models.common.TaskResponseDto;

public class CreateTaskResponseDto extends TaskResponseDto {

    public CreateTaskResponseDto(TaskDto task) {
        super(task);
    }
}
