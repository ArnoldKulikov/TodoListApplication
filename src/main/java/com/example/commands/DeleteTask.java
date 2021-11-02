package com.example.commands;

import com.example.interfaces.Command;
import com.example.core.Task;
import com.example.dictionaries.ErrorList;

import java.util.List;
import java.util.Optional;

public class DeleteTask implements Command {
    @Override
    public void execute(List<Task> taskList, String commandLine) {
        if (!commandLine.matches("^[0-9]*$")) System.out.println(ErrorList.ERRORLIST.get("notTaskId"));
        else {
            Optional<Task> foundTask = taskList
                    .stream()
                    .filter(t -> t.getId() == Integer.parseInt(commandLine))
                    .findFirst();
            if (foundTask.isPresent()) taskList.remove(foundTask);
            else System.out.println(ErrorList.ERRORLIST.get("taskNotFound"));
        }
    }
}
