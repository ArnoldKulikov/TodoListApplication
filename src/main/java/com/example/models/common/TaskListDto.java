package com.example.models.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskListDto {
    private List<TaskDto> tasks = new ArrayList<>();

}