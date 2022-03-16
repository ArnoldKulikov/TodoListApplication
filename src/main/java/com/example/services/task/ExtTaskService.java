package com.example.services.task;

import com.example.models.common.ExtTaskDto;

import java.util.List;

public interface ExtTaskService {

    List<ExtTaskDto> getTaskList();

    List<ExtTaskDto> getAllTaskList();

}
