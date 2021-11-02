package com.example.commands;

import com.example.dictionaries.ErrorList;
import com.example.interfaces.Command;
import com.example.core.Task;

import java.util.List;

public class SearchTask implements Command {
    @Override
    public void execute(List<Task> taskList, String[] commandLine) {
        if (commandLine.length != 1)
        taskList
                .stream()
                .filter(t -> t.getDescription().contains(commandLine[1]))
                .forEach(System.out::println);
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("search");
    }
}
