package com.example.commands;

import com.example.core.TaskList;
import com.example.interfaces.Command;

public class SearchTask implements Command {

    private TaskList taskList;

    public SearchTask(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(String[] commandLine) {
        if (commandLine.length != 1) {
            taskList.getTaskList()
                    .stream()
                    .filter(t -> t.getDescription().contains(commandLine[1]))
                    .forEach(System.out::println);
        }
    }

    @Override
    public boolean checkCommand(String command) {
        return "search".equals(command);
    }
}
