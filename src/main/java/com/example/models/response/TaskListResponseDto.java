package com.example.models.response;

import com.example.models.common.TaskDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class TaskListResponseDto {

    @NotNull
    private List<TaskDto> tasks = new ArrayList<>();

}