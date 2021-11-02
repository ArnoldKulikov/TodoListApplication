package com.example.commands;

import com.example.interfaces.Command;
import com.example.core.Task;

import java.util.List;

public class SearchTask implements Command {
    @Override
    public void execute(List<Task> taskList, String commandLine) {
        taskList
                .stream()
                .filter(t -> t.getDescription().contains(commandLine))
                .forEach(System.out::println);
    }
}
