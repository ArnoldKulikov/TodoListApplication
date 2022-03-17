package com.example.services.task;

import com.example.models.common.ExtTaskDto;
import com.example.models.common.ExtTaskListDto;

import java.util.List;
import java.util.concurrent.Future;

public interface ExtTaskService {

    List<ExtTaskDto> getTaskList();

    Future<ExtTaskListDto> getAllTaskList();

}
