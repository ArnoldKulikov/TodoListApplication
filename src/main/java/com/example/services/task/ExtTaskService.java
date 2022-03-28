package com.example.services.task;

import com.example.models.common.TaskDto;

import java.util.List;
import java.util.concurrent.Future;

public interface ExtTaskService {

    Future<List<TaskDto>> getTaskList();

    Future<List<TaskDto>> getAllTaskList();

}
