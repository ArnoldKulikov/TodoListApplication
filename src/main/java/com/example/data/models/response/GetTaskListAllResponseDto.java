package com.example.data.models.response;

import com.example.data.models.common.TaskDto;
import com.example.data.models.common.TaskListResponseDto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class GetTaskListAllResponseDto extends TaskListResponseDto {

    public GetTaskListAllResponseDto(@NotNull List<TaskDto> taskList) {
        super(taskList);
    }
}
