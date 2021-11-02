package com.example.commands;

import com.example.interfaces.Command;
import com.example.core.Task;

import java.util.List;

public class AddTask implements Command {

    @Override
    public void execute(List<Task> taskList, String commandLine) {
        int nextTaskId = Task.nextTaskId;

        Task task = new Task(nextTaskId, false, commandLine);
        taskList.add(task);
        Task.nextTaskId = ++nextTaskId;
    }
}
