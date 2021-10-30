package com.example.commands;

import com.example.core.Task;

import java.util.ArrayList;

public class AddTaskInTaskList {

    public void addTask(ArrayList<Task> taskList, int newTaskId, String newTask) {
        Task receivedTask = new Task(newTaskId, false, newTask);

        taskList.add(receivedTask);
    }
}
