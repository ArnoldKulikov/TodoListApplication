package com.example.interfaces;

import com.example.core.Task;

import java.util.List;

public interface Command {

    void execute(List<Task> taskList, String commandLine);
}
