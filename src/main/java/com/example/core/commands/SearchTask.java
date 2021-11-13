package com.example.core.commands;

import com.example.data.base.TaskList;
import com.example.data.models.CommandLine;
import com.example.interfaces.Command;

public class SearchTask implements Command {

    private TaskList taskList;

    public SearchTask(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(CommandLine commandLine) {
        if (commandLine.getDescription() != null) {
            taskList.getTaskList()
                    .stream()
                    .filter(t -> t.getDescription().contains(commandLine.getDescription()))
                    .forEach(System.out::println);
        }
    }

    @Override
    public boolean checkCommand(String command) {
        return "search".equals(command);
    }
}
